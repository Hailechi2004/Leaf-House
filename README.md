# LeafHouse - Ứng dụng Nhận diện Bệnh Lá Cà Chua

## 📱 Giới thiệu

**LeafHouse** là ứng dụng Android thông minh giúp nông dân và người trồng cà chua phát hiện bệnh trên lá cây một cách nhanh chóng và chính xác. Sử dụng công nghệ Học sâu (Deep Learning) với TensorFlow Lite, ứng dụng có thể nhận diện 10 loại bệnh khác nhau chỉ bằng một bức ảnh.

## 🎯 Mục tiêu

- ✅ **Nhận dạng bệnh chính xác**: Phát hiện 10 loại bệnh phổ biến trên lá cà chua
- ✅ **Giao diện thân thiện**: Dễ dàng sử dụng cho mọi người
- ✅ **Hoạt động offline**: Không cần kết nối Internet
- ✅ **Tư vấn chi tiết**: Cung cấp thông tin về triệu chứng, nguyên nhân và cách điều trị

## 🏥 Các loại bệnh được nhận diện

1. **Bacterial_spot** - Đốm vi khuẩn
2. **Late_blight** - Bệnh mốc sương
3. **Septoria_leaf_spot** - Đốm lá Septoria
4. **Yellow_Leaf_Curl_Virus** - Virus cuộn lá vàng
5. **Early_blight** - Bệnh héo sớm
6. **Leaf_Mold** - Nấm lá
7. **Spider_mites** - Nhện đỏ
8. **Healthy** - Lá khỏe mạnh
9. **Mosaic_virus** - Virus khảm lá
10. **Target_Spot** - Đốm mục tiêu

## 🚀 Tính năng chính

### 1. Chụp ảnh hoặc Upload từ thư viện

- Sử dụng Camera để chụp trực tiếp lá cây
- Chọn ảnh có sẵn từ thư viện điện thoại

### 2. Nhận diện bệnh tức thì

- AI phân tích ảnh trong vài giây
- Hiển thị kết quả với độ chính xác cao

### 3. Thông tin chi tiết

- **Tên bệnh**: Tên tiếng Việt và tiếng Anh
- **Độ chính xác**: Phần trăm độ tin cậy của AI
- **Mô tả**: Thông tin tổng quan về bệnh
- **Triệu chứng**: Dấu hiệu nhận biết bệnh
- **Nguyên nhân**: Tác nhân gây bệnh
- **Cách điều trị**: Hướng dẫn xử lý bệnh
- **Phòng ngừa**: Biện pháp phòng tránh

### 4. Hoạt động Offline

- Tất cả tính năng đều hoạt động mà không cần Internet
- Model AI được tích hợp sẵn trong ứng dụng

## 📋 Yêu cầu hệ thống

- **Android**: 7.0 (API 24) trở lên
- **RAM**: Tối thiểu 2GB
- **Dung lượng**: ~50MB cho ứng dụng và model
- **Camera**: Khuyến nghị có camera sau

## 🛠️ Cài đặt và Sử dụng

### Cài đặt

1. Download file APK từ Releases
2. Cài đặt ứng dụng trên điện thoại Android
3. Cấp quyền Camera và Thư viện ảnh khi được yêu cầu

### Hướng dẫn sử dụng

#### Bước 1: Mở ứng dụng LeafHouse

- Giao diện chính sẽ hiển thị với 2 nút: **Camera** và **Upload file**

#### Bước 2: Chọn nguồn ảnh

**Tùy chọn A: Chụp ảnh mới**

1. Nhấn nút **Camera**
2. Điều chỉnh góc máy để chụp lá cây rõ ràng
3. Chụp ảnh

**Tùy chọn B: Chọn ảnh từ thư viện**

1. Nhấn nút **Upload file**
2. Chọn ảnh lá cây từ thư viện

#### Bước 3: Xem kết quả

- Ảnh sẽ hiển thị trên màn hình
- Kết quả nhận diện gồm:
  - **Tên bệnh** (dòng 1)
  - **Độ chính xác** (dòng 2, ví dụ: "Độ chính xác: 95%")

#### Bước 4: Xem chi tiết và đề xuất

1. Nhấn nút **Xem chi tiết bệnh**
2. Đọc thông tin chi tiết về:
   - Mô tả bệnh
   - Triệu chứng nhận biết
   - Nguyên nhân gây bệnh
   - Cách điều trị hiệu quả
   - Biện pháp phòng ngừa

## 💡 Mẹo sử dụng hiệu quả

### Để có kết quả chính xác nhất:

1. ✅ Chụp ảnh trong điều kiện ánh sáng tốt (ban ngày, ánh sáng tự nhiên)
2. ✅ Lá cây chiếm phần lớn trong khung hình
3. ✅ Ảnh rõ nét, không bị mờ
4. ✅ Chụp đúng phần lá có triệu chứng bệnh
5. ❌ Tránh chụp quá xa hoặc quá tối
6. ❌ Tránh ảnh bị mờ hoặc chói sáng

## 🔧 Công nghệ sử dụng

- **Ngôn ngữ**: Kotlin
- **Framework UI**: Android SDK, Material Design
- **AI/ML**:
  - TensorFlow Lite 2.13.0
  - Model được train với dữ liệu 10 loại bệnh lá cà chua
- **Camera**: CameraX Library
- **Hình ảnh**: Glide

## 📊 Model AI

### Thông tin Model

- **Định dạng**: TensorFlow Lite (.tflite)
- **Input**: Ảnh RGB 256x256 pixels
- **Output**: 10 classes với confidence score
- **Số lượng labels**: 10 loại bệnh

### Chuyển đổi Model

Model gốc (.h5) đã được chuyển đổi sang TensorFlow Lite (.tflite) để:

- Giảm kích thước model
- Tăng tốc độ inference
- Tối ưu cho thiết bị di động
- Hoạt động offline

## 📁 Cấu trúc dự án

```
LeafHouse/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/leafhouse/
│   │   │   │   ├── MainActivity.kt           # Màn hình chính
│   │   │   │   ├── DiseaseDetailActivity.kt  # Màn hình chi tiết bệnh
│   │   │   │   ├── ImageClassifier.kt        # Xử lý AI/ML
│   │   │   │   └── DiseaseInfo.kt            # Database thông tin bệnh
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   └── activity_disease_detail.xml
│   │   │   │   ├── drawable/
│   │   │   │   │   └── logo.png
│   │   │   │   └── values/
│   │   │   │       └── strings.xml
│   │   │   ├── assets/
│   │   │   │   ├── model.tflite              # Model AI
│   │   │   │   └── labels.txt                # Danh sách labels
│   │   │   └── AndroidManifest.xml
│   └── build.gradle.kts
├── gradle/
├── model.h5                                   # Model gốc (để train)
├── model.tflite                              # Model đã convert
└── README.md
```

## 🔐 Quyền cần thiết

Ứng dụng yêu cầu các quyền sau:

- **CAMERA**: Chụp ảnh lá cây
- **READ_MEDIA_IMAGES**: Đọc ảnh từ thư viện (Android 13+)
- **READ_EXTERNAL_STORAGE**: Đọc ảnh từ thư viện (Android 12 trở xuống)

## 🐛 Xử lý sự cố

### Ứng dụng không nhận diện được

- Kiểm tra ảnh có rõ nét không
- Đảm bảo lá cây chiếm phần lớn khung hình
- Thử chụp lại với ánh sáng tốt hơn

### Không thể chụp ảnh

- Kiểm tra quyền Camera đã được cấp chưa
- Vào Settings → Apps → LeafHouse → Permissions → Bật Camera

### Không thể chọn ảnh từ thư viện

- Kiểm tra quyền Storage/Media đã được cấp chưa
- Vào Settings → Apps → LeafHouse → Permissions → Bật Photos and Media

## 📝 Ghi chú phát triển

### Build từ source code

```bash
# Clone repository
git clone <repository-url>
cd LeafHouse

# Mở bằng Android Studio
# Sync Gradle
# Build APK: Build → Build Bundle(s) / APK(s) → Build APK(s)
```

### Yêu cầu development

- Android Studio Hedgehog hoặc mới hơn
- JDK 11 hoặc mới hơn
- Android SDK 36
- Gradle 8.13.1

## 📞 Liên hệ & Hỗ trợ

Nếu bạn gặp vấn đề hoặc có đề xuất cải thiện, vui lòng:

- Mở Issue trên GitHub
- Liên hệ email: [email của bạn]

## 📄 Giấy phép

[Chọn loại license phù hợp: MIT, Apache 2.0, GPL, etc.]

## 🙏 Lời cảm ơn

- Cảm ơn cộng đồng TensorFlow và Android
- Cảm ơn các nhà nghiên cứu đã tạo dataset bệnh lá cà chua
- Cảm ơn tất cả những người đã đóng góp cho dự án

---

## ⚠️ Lưu ý quan trọng

- Kết quả nhận diện chỉ mang tính chất tham khảo
- Nên kết hợp với kinh nghiệm thực tế và tư vấn chuyên gia
- Luôn kiểm tra kỹ trước khi áp dụng biện pháp điều trị
- Ứng dụng không thay thế cho chuyên gia nông nghiệp

---

**Phiên bản**: 1.0  
**Ngày cập nhật**: November 2025  
**Tác giả**: [Tên của bạn]
