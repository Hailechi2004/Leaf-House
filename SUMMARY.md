# Tóm tắt Phát triển Ứng dụng LeafHouse

## ✅ Đã hoàn thành

### 1. Cấu trúc dự án

- ✅ Kiểm tra và xác nhận cấu trúc Android project
- ✅ Model files (model.tflite, labels.txt) đã có trong assets
- ✅ Cấu hình Gradle đã được cập nhật

### 2. MainActivity - Màn hình chính

**Chức năng:**

- ✅ Chụp ảnh bằng Camera
- ✅ Chọn ảnh từ thư viện
- ✅ Hiển thị ảnh đã chọn
- ✅ Nhận diện bệnh tự động khi có ảnh
- ✅ Hiển thị kết quả: Tên bệnh + Độ chính xác
- ✅ Button "Xem chi tiết bệnh" xuất hiện sau khi có kết quả
- ✅ Xử lý permissions Camera và Storage

**Files liên quan:**

- `app/src/main/java/com/example/leafhouse/MainActivity.kt`
- `app/src/main/res/layout/activity_main.xml`

### 3. DiseaseDetailActivity - Màn hình chi tiết

**Chức năng:**

- ✅ Nhận dữ liệu (tên bệnh, độ chính xác) từ MainActivity
- ✅ Hiển thị thông tin chi tiết từ Database
- ✅ Layout đẹp mắt với CardView
- ✅ Nút quay lại

**Thông tin hiển thị:**

- ✅ Tên bệnh (Tiếng Việt)
- ✅ Độ chính xác
- ✅ 📋 Mô tả
- ✅ 🔍 Triệu chứng
- ✅ ⚠️ Nguyên nhân
- ✅ 💊 Cách điều trị (nền xanh lá nhẹ)
- ✅ 🛡️ Phòng ngừa (nền cam nhẹ)

**Files liên quan:**

- `app/src/main/java/com/example/leafhouse/DiseaseDetailActivity.kt`
- `app/src/main/res/layout/activity_disease_detail.xml`

### 4. ImageClassifier - Xử lý AI

**Chức năng:**

- ✅ Load model TensorFlow Lite
- ✅ Load labels từ labels.txt
- ✅ Xử lý ảnh (resize 256x256, normalize)
- ✅ Chạy inference
- ✅ Trả về label và confidence

**Files liên quan:**

- `app/src/main/java/com/example/leafhouse/ImageClassifier.kt`

### 5. DiseaseInfo & Database

**Chức năng:**

- ✅ Data class chứa thông tin bệnh
- ✅ Database với 10 loại bệnh hoàn chỉnh:
  1. Bacterial_spot - Đốm vi khuẩn
  2. Late_blight - Bệnh mốc sương
  3. Septoria_leaf_spot - Đốm lá Septoria
  4. Yellow_Leaf_Curl_Virus - Virus cuộn lá vàng
  5. Early_blight - Bệnh héo sớm
  6. Leaf_Mold - Nấm lá
  7. Spider_mites - Nhện đỏ
  8. Healthy - Lá khỏe mạnh
  9. Mosaic_virus - Virus khảm lá
  10. Target_Spot - Đốm mục tiêu

**Mỗi bệnh có đầy đủ:**

- ✅ Tên tiếng Việt
- ✅ Mô tả chi tiết
- ✅ Triệu chứng
- ✅ Nguyên nhân
- ✅ Cách điều trị
- ✅ Phòng ngừa

**Files liên quan:**

- `app/src/main/java/com/example/leafhouse/DiseaseInfo.kt`

### 6. AndroidManifest

**Đã cấu hình:**

- ✅ Permissions: CAMERA, READ_MEDIA_IMAGES, READ_EXTERNAL_STORAGE
- ✅ MainActivity (exported=true, LAUNCHER)
- ✅ DiseaseDetailActivity (exported=false, parentActivity)
- ✅ FileProvider cho Camera

**Files liên quan:**

- `app/src/main/AndroidManifest.xml`
- `app/src/main/res/xml/file_paths.xml`

### 7. Dependencies & Configuration

**Đã thêm:**

- ✅ TensorFlow Lite 2.13.0
- ✅ TensorFlow Lite Support & Metadata
- ✅ CameraX libraries
- ✅ Glide (image loading)
- ✅ CardView (cho UI)
- ✅ Material Design components

**Files liên quan:**

- `app/build.gradle.kts`
- `gradle/libs.versions.toml`

### 8. Assets & Resources

**Có sẵn:**

- ✅ model.tflite (trong assets)
- ✅ labels.txt (trong assets)
- ✅ logo.png (trong drawable)

### 9. Documentation

**Đã tạo:**

- ✅ README.md với hướng dẫn đầy đủ
- ✅ SUMMARY.md (file này)

---

## 🎯 Quy trình hoạt động

### Quy trình người dùng:

```
1. Mở App (MainActivity)
   ↓
2. Chọn Camera HOẶC Upload file
   ↓
3. Chọn/Chụp ảnh lá cây
   ↓
4. Ảnh hiển thị → AI tự động phân tích
   ↓
5. Kết quả xuất hiện:
   - Tên bệnh
   - Độ chính xác (%)
   - Button "Xem chi tiết bệnh"
   ↓
6. Click "Xem chi tiết bệnh"
   ↓
7. Màn hình DiseaseDetailActivity hiển thị:
   - Thông tin đầy đủ về bệnh
   - Cách điều trị
   - Phòng ngừa
```

### Quy trình kỹ thuật:

```
MainActivity
  ↓ (User chọn ảnh)
  ↓
loadImageFromUri()
  ↓
classifyImage(bitmap)
  ↓
ImageClassifier.classifyImage()
  ↓ (Load model.tflite + labels.txt)
  ↓
TensorFlow Lite inference
  ↓
Trả về Classification(label, confidence)
  ↓
MainActivity hiển thị kết quả
  ↓ (User click "Xem chi tiết")
  ↓
DiseaseDetailActivity
  ↓
DiseaseDatabase.getDiseaseInfo(diseaseName)
  ↓
Hiển thị thông tin chi tiết
```

---

## 📱 Tính năng theo yêu cầu

| Yêu cầu               | Trạng thái    | Ghi chú                      |
| --------------------- | ------------- | ---------------------------- |
| Sử dụng Camera        | ✅ Hoàn thành | CameraX + FileProvider       |
| Upload từ thư viện    | ✅ Hoàn thành | ActivityResultContracts      |
| Nhận diện bệnh        | ✅ Hoàn thành | TensorFlow Lite model        |
| Hiển thị tên bệnh     | ✅ Hoàn thành | Replace "\_" thành " "       |
| Hiển thị độ chính xác | ✅ Hoàn thành | Confidence × 100%            |
| Button xem chi tiết   | ✅ Hoàn thành | Xuất hiện sau khi có kết quả |
| Màn hình chi tiết     | ✅ Hoàn thành | ScrollView với CardViews     |
| Thông tin bệnh đầy đủ | ✅ Hoàn thành | 10 bệnh × 6 trường thông tin |
| Đề xuất chăm sóc      | ✅ Hoàn thành | Treatment + Prevention       |
| Hoạt động offline     | ✅ Hoàn thành | Model tích hợp sẵn           |

---

## 🎨 Giao diện

### MainActivity

- Header: Logo + "Leaf House"
- ImageView: Hiển thị ảnh (mặc định: icon camera)
- TextView 1: Tên bệnh
- TextView 2: Độ chính xác
- Button 1: Camera
- Button 2: Upload file
- Button 3: Xem chi tiết bệnh (ẩn/hiện động)

### DiseaseDetailActivity

- Header: Logo + "Leaf House" (nền màu primary)
- Card 1: Tên bệnh + Độ chính xác
- Card 2: 📋 Mô tả
- Card 3: 🔍 Triệu chứng
- Card 4: ⚠️ Nguyên nhân
- Card 5: 💊 Cách điều trị (nền xanh lá nhạt)
- Card 6: 🛡️ Phòng ngừa (nền cam nhạt)
- Button: Quay lại

---

## 🔐 Permissions

| Permission            | Mục đích            | API Level |
| --------------------- | ------------------- | --------- |
| CAMERA                | Chụp ảnh lá cây     | All       |
| READ_MEDIA_IMAGES     | Đọc ảnh từ thư viện | 33+       |
| READ_EXTERNAL_STORAGE | Đọc ảnh từ thư viện | 24-32     |

---

## 📊 Model AI

- **File**: model.tflite
- **Input Size**: 256×256×3 (RGB)
- **Normalization**: [0, 255] → [0, 1]
- **Output**: 10 classes
- **Labels**: labels.txt (10 dòng)

---

## 🚀 Cách build & chạy

### Yêu cầu:

- Android Studio Hedgehog+
- JDK 11+
- Android SDK 36

### Các bước:

1. Mở project trong Android Studio
2. Sync Gradle (tự động)
3. Chọn device/emulator
4. Run (Shift+F10)

### Build APK:

```
Build → Build Bundle(s) / APK(s) → Build APK(s)
```

---

## ⚙️ Cấu hình kỹ thuật

- **minSdk**: 24 (Android 7.0)
- **targetSdk**: 36 (Android 14+)
- **compileSdk**: 36
- **Kotlin**: 2.0.21
- **Gradle**: 8.13.1
- **AGP**: 8.13.1

---

## 📝 Notes

1. **Model đã có sẵn**: model.tflite, labels.txt trong assets folder
2. **Hoạt động offline**: Tất cả tính năng không cần Internet
3. **UI responsive**: ScrollView cho màn hình chi tiết
4. **Error handling**: Try-catch cho file IO, model inference
5. **Permission handling**: Runtime permissions cho Android 6.0+

---

## 🎉 Kết luận

Ứng dụng LeafHouse đã được phát triển hoàn chỉnh theo đúng yêu cầu:

- ✅ Nhận diện 10 loại bệnh lá cà chua
- ✅ Camera + Upload từ thư viện
- ✅ Hiển thị tên bệnh + độ chính xác
- ✅ Chi tiết bệnh + đề xuất chăm sóc
- ✅ Hoạt động offline
- ✅ Giao diện thân thiện, dễ sử dụng

Sẵn sàng để build và test!
