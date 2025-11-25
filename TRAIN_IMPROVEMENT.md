# Hướng dẫn cải thiện Model Leaf House

## Vấn đề hiện tại

- Model dự đoán sai: Late_blight thay vì Bacterial_spot
- Độ chính xác thực tế: ~33% (1/3 ảnh đúng)
- Model quá tự tin (95-98%) nhưng sai → Overfitting

## Nguyên nhân

1. **Dataset thiếu đa dạng**: Ảnh train có background/style khác ảnh test
2. **Overfitting**: Model học thuộc lòng dataset train, không tổng quát hóa tốt
3. **Data imbalance**: Một số class có ít ảnh hơn

## Giải pháp - Train lại Model

### Option 1: Tăng cường Data Augmentation (NHANH - khuyên dùng)

Mở `final_model.ipynb` trong Google Colab, tìm đến cell tạo `ImageDataGenerator` và sửa:

**TRƯỚC:**

```python
gen = ImageDataGenerator(horizontal_flip=False, rotation_range=20, width_shift_range=.1,
                    height_shift_range=.1, zoom_range=.2, fill_mode='constant', cval=255)
```

**SAU (cải tiến):**

```python
gen = ImageDataGenerator(
    horizontal_flip=True,           # Lật ngang
    vertical_flip=True,             # Lật dọc
    rotation_range=30,              # Xoay ±30 độ
    width_shift_range=0.2,          # Dịch ngang 20%
    height_shift_range=0.2,         # Dịch dọc 20%
    zoom_range=0.3,                 # Zoom 30%
    shear_range=0.2,                # Biến dạng 20%
    brightness_range=[0.7, 1.3],    # Thay đổi độ sáng
    fill_mode='nearest'             # Lấp đầy pixel gần nhất
)
```

Và sửa train generator:

```python
trgen=ImageDataGenerator(
    horizontal_flip=True,
    vertical_flip=True,
    rotation_range=20,
    width_shift_range=0.15,
    height_shift_range=0.15,
    zoom_range=0.2,
    brightness_range=[0.8, 1.2]
)
```

### Option 2: Giảm Overfitting

**Tăng Dropout** trong model architecture (cell `make_model_with_2se_ca`):

**TRƯỚC:**

```python
x=Dropout(rate=.4, seed=123)(x)
```

**SAU:**

```python
x=Dropout(rate=.5, seed=123)(x)  # Tăng từ 0.4 → 0.5
```

**Giảm epochs hoặc thêm Early Stopping:**

```python
early_stop = tf.keras.callbacks.EarlyStopping(
    monitor='val_loss',
    patience=5,              # Dừng sau 5 epochs không cải thiện
    restore_best_weights=True
)
```

### Option 3: Thêm dữ liệu Bacterial_spot

1. Tải thêm ảnh Bacterial_spot từ nguồn khác
2. Thêm vào dataset train
3. Train lại

### Option 4: Giảm Learning Rate và Train lại từ model hiện tại

Trong cell tạo model:

```python
lr = 0.00005  # Giảm từ 0.0001 → 0.00005
model = make_model_with_2se_ca(img_size, lr, class_count)
```

Load model cũ và train tiếp:

```python
from tensorflow.keras.models import load_model

# Load model cũ
model = load_model('/content/drive/MyDrive/Colab Notebooks/main_full/model_EffNet_B4_SeCa.h5',
                   custom_objects={'SEBlock': SEBlock, 'CABlock': CABlock})

# Train tiếp với learning rate thấp
model.compile(optimizer=Adamax(learning_rate=0.00003),
              loss='categorical_crossentropy',
              metrics=['accuracy', F1_score, tf.keras.metrics.AUC(name='auc')])

history = model.fit(train_gen, validation_data=valid_gen, epochs=10, callbacks=[early_stop])
```

## Các bước thực hiện

### CÁCH NHANH NHẤT (30-60 phút):

1. **Mở Google Colab**: https://colab.research.google.com/
2. **Upload file** `final_model.ipynb`
3. **Sửa các cell** theo Option 1 + Option 2 ở trên
4. **Chạy toàn bộ notebook** (Runtime → Run all)
5. **Chờ training** (khoảng 20-40 phút với GPU)
6. **Download** file `.tflite` mới về
7. **Copy** vào `app/src/main/assets/model.tflite`
8. **Build lại** app

### Chi tiết từng bước:

```bash
# Bước 1: Training xong, download model mới
# Trong Colab đã có code download rồi

# Bước 2: Copy model mới vào project
Copy file model.tflite mới từ Downloads vào:
C:\microsoft Visual Studio Code\LeafHouse\app\src\main\assets\model.tflite

# Bước 3: Build lại app
cd "c:\microsoft Visual Studio Code\LeafHouse"
$env:JAVA_HOME = "C:\Program Files\Java\jdk-17"
.\gradlew.bat assembleDebug

# Bước 4: Cài lại app
adb install -r app\build\outputs\apk\debug\app-debug.apk
```

## Kỳ vọng sau khi cải thiện

- Độ chính xác tăng từ ~33% → **70-85%**
- Model ít tự tin hơn khi không chắc chắn
- Phân biệt tốt hơn giữa Bacterial_spot và Late_blight

## Lưu ý

- Training trên Google Colab FREE: giới hạn GPU (~12 giờ/ngày)
- Nếu timeout, lưu checkpoint và train tiếp
- Kiểm tra validation loss - nếu tăng liên tục = overfitting

## Nếu không muốn train lại

Có thể tìm model pre-trained tốt hơn:

- PlantVillage models trên GitHub
- TensorFlow Hub plant disease models
- Kaggle pre-trained models

Hoặc chấp nhận độ chính xác hiện tại và ghi chú trong báo cáo:
"Model đạt độ chính xác 33% trên test set do giới hạn về dataset và thời gian training"
