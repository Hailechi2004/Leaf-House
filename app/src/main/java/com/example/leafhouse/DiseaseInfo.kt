package com.example.leafhouse

data class DiseaseInfo(
    val name: String,
    val description: String,
    val symptoms: String,
    val causes: String,
    val treatment: String,
    val prevention: String
)

object DiseaseDatabase {
    private val diseaseMap = mapOf(
        "Bacterial_spot" to DiseaseInfo(
            name = "Đốm vi khuẩn (Bacterial Spot)",
            description = "Bệnh đốm vi khuẩn là một trong những bệnh phổ biến nhất trên cây cà chua, gây ra bởi vi khuẩn Xanthomonas.",
            symptoms = "- Xuất hiện các đốm nhỏ màu nâu đen trên lá\n- Các đốm có viền vàng xung quanh\n- Lá bị vàng và rụng sớm\n- Quả xuất hiện các đốm nâu nhỏ, có vòng trắng xung quanh",
            causes = "- Vi khuẩn Xanthomonas campestris\n- Độ ẩm cao và nhiệt độ ấm (24-30°C)\n- Mưa nhiều và tưới nước không đúng cách\n- Hạt giống bị nhiễm bệnh",
            treatment = "- Loại bỏ và tiêu hủy các phần cây bị bệnh\n- Phun thuốc kháng sinh chứa đồng (copper-based)\n- Sử dụng thuốc sinh học như Bacillus subtilis\n- Cải thiện thoát nước và tuần hoàn không khí",
            prevention = "- Sử dụng hạt giống sạch bệnh\n- Luân canh cây trồng (không trồng cà chua liên tục)\n- Tưới nước nhỏ giọt, tránh tưới lên lá\n- Giữ khoảng cách hợp lý giữa các cây\n- Khử trùng dụng cụ nông nghiệp"
        ),
        
        "Late_blight" to DiseaseInfo(
            name = "Bệnh mốc sương (Late Blight)",
            description = "Bệnh mốc sương là bệnh nguy hiểm nhất trên cây cà chua, có thể phá hủy toàn bộ vườn cây trong vài ngày nếu không được kiểm soát kịp thời.",
            symptoms = "- Các đốm màu nâu xám trên lá, thường xuất hiện ở mép lá\n- Mặt dưới lá có lớp nấm mốc màu trắng\n- Thân và cành chuyển màu nâu đen\n- Quả xuất hiện đốm nâu cứng, không thối",
            causes = "- Nấm Phytophthora infestans\n- Độ ẩm cao (trên 90%)\n- Nhiệt độ mát mẻ (15-25°C)\n- Mưa kéo dài hoặc sương mù\n- Thời tiết âm u",
            treatment = "- Loại bỏ và đốt tất cả các phần cây bị bệnh ngay lập tức\n- Phun fungicide chứa đồng hoặc Mancozeb\n- Sử dụng thuốc trừ nấm hệ thống như Metalaxyl\n- Cải thiện thoát nước và độ thông thoáng",
            prevention = "- Chọn giống kháng bệnh\n- Tránh trồng quá dày\n- Tưới nước vào buổi sáng, tránh tưới lên lá\n- Phun thuốc phòng bệnh trước mùa mưa\n- Loại bỏ cỏ dại xung quanh vườn\n- Không trồng cà chua gần khoai tây"
        ),
        
        "Septoria_leaf_spot" to DiseaseInfo(
            name = "Đốm lá Septoria",
            description = "Bệnh đốm lá Septoria là bệnh nấm phổ biến, thường xuất hiện sau khi cây ra quả, ảnh hưởng nghiêm trọng đến năng suất.",
            symptoms = "- Các đốm tròn nhỏ màu nâu với viền sẫm\n- Trung tâm đốm có màu xám nhạt với các chấm đen nhỏ\n- Bệnh bắt đầu từ lá dưới và lan dần lên trên\n- Lá vàng và rụng sớm",
            causes = "- Nấm Septoria lycopersici\n- Nhiệt độ ấm (20-25°C)\n- Độ ẩm cao\n- Nước bắn lên lá khi tưới\n- Cây trồng quá dày",
            treatment = "- Loại bỏ lá bị bệnh, đặc biệt là lá dưới\n- Phun fungicide chứa Chlorothalonil hoặc Mancozeb\n- Tăng cường thoát nước và thông thoáng\n- Bón phân cân đối để cây khỏe mạnh",
            prevention = "- Trồng cây cách nhau đủ rộng (60-90cm)\n- Tỉa bỏ lá dưới tiếp xúc với đất\n- Tưới nhỏ giọt thay vì tưới phun\n- Phủ mulch để tránh đất bắn lên lá\n- Luân canh với cây họ khác"
        ),
        
        "Yellow_Leaf_Curl_Virus" to DiseaseInfo(
            name = "Virus cuộn lá vàng (TYLCV)",
            description = "Virus cuộn lá vàng là bệnh virus nghiêm trọng, lây truyền qua ruồi trắng, gây thiệt hại lớn cho sản lượng cà chua.",
            symptoms = "- Lá non cuộn lại và cong vênh\n- Lá chuyển màu vàng, đặc biệt ở gân lá\n- Cây còi cọc, sinh trưởng chậm\n- Giảm hoặc không ra hoa\n- Quả nhỏ, biến dạng",
            causes = "- Virus TYLCV (Tomato Yellow Leaf Curl Virus)\n- Lây truyền qua ruồi trắng (Bemisia tabaci)\n- Cây giống bị nhiễm virus\n- Nhiệt độ cao (25-30°C) tạo điều kiện cho ruồi trắng phát triển",
            treatment = "- KHÔNG CÓ THUỐC chữa virus, chỉ có thể phòng ngừa\n- Loại bỏ và tiêu hủy cây bị bệnh ngay\n- Kiểm soát ruồi trắng bằng thuốc trừ sâu\n- Sử dụng bẫy dính màu vàng\n- Phun dầu neem hoặc xà phòng côn trùng",
            prevention = "- Sử dụng giống kháng virus\n- Trồng cây khỏe mạnh từ vườn ươm sạch bệnh\n- Lưới chắn côn trùng cho vườn ươm\n- Loại bỏ cỏ dại - nơi ẩn náu của ruồi trắng\n- Trồng cây bẫy ruồi trắng xung quanh vườn\n- Tránh trồng cà chua gần bí, dưa"
        ),
        
        "Early_blight" to DiseaseInfo(
            name = "Bệnh héo sớm (Early Blight)",
            description = "Bệnh héo sớm là bệnh nấm phổ biến trên cà chua, gây hại mạnh trong điều kiện nóng ẩm, làm giảm năng suất và chất lượng quả.",
            symptoms = "- Đốm tròn màu nâu có vòng đồng tâm như bia bắn\n- Bắt đầu từ lá già ở phần dưới cây\n- Vùng quanh đốm chuyển vàng\n- Lá khô, chết và rụng\n- Thân xuất hiện vết loét màu nâu\n- Quả có đốm nâu lõm gần cuống",
            causes = "- Nấm Alternaria solani\n- Nhiệt độ ấm (24-29°C)\n- Độ ẩm cao, sương đọng\n- Cây thiếu dinh dưỡng, đặc biệt là Nitơ\n- Cây bị stress do hạn hán",
            treatment = "- Loại bỏ lá bị bệnh\n- Phun fungicide chứa Chlorothalonil, Mancozeb\n- Sử dụng thuốc trừ nấm hệ thống như Azoxystrobin\n- Bón phân đầy đủ, cân đối\n- Tưới nước đều đặn",
            prevention = "- Sử dụng giống kháng bệnh\n- Luân canh 3-4 năm\n- Tỉa bỏ lá già, lá chạm đất\n- Phủ mulch hoặc rơm rạ\n- Giữ cây khỏe mạnh bằng phân bón đầy đủ\n- Tưới nhỏ giọt, tránh làm ướt lá\n- Khoảng cách trồng hợp lý"
        ),
        
        "Leaf_Mold" to DiseaseInfo(
            name = "Nấm lá (Leaf Mold)",
            description = "Bệnh nấm lá thường xảy ra trong nhà kính hoặc vùng ẩm ướt, ảnh hưởng đến khả năng quang hợp của cây.",
            symptoms = "- Mặt trên lá xuất hiện đốm vàng nhạt\n- Mặt dưới lá có lớp nấm mốc màu xám đến nâu vàng\n- Lá cong vênh và cuộn lại\n- Lá chuyển nâu và chết\n- Hiếm khi ảnh hưởng đến quả",
            causes = "- Nấm Passalora fulva (Fulvia fulva)\n- Độ ẩm rất cao (trên 85%)\n- Nhiệt độ ấm (22-24°C)\n- Thông gió kém\n- Nhà kính kín, ẩm",
            treatment = "- Cải thiện thông gió, giảm độ ẩm\n- Loại bỏ lá bị bệnh\n- Phun fungicide chứa Chlorothalonil\n- Giảm tưới nước, tránh tưới lên lá\n- Tăng khoảng cách giữa cây",
            prevention = "- Trồng giống kháng bệnh\n- Đảm bảo thông gió tốt trong nhà kính\n- Kiểm soát độ ẩm dưới 85%\n- Tưới vào buổi sáng\n- Tỉa cành để tăng thông thoáng\n- Khử trùng nhà kính sau mỗi vụ"
        ),
        
        "Spider_mites" to DiseaseInfo(
            name = "Nhện đỏ (Spider Mites)",
            description = "Nhện đỏ là loại sâu bệnh nhỏ li ti, gây hại nghiêm trọng bằng cách hút nhựa cây, đặc biệt trong điều kiện khô hạn.",
            symptoms = "- Lá xuất hiện các chấm vàng nhỏ\n- Mặt dưới lá có mạng nhện mỏng\n- Lá chuyển màu vàng nâu, khô héo\n- Cây sinh trưởng kém\n- Có thể thấy nhện nhỏ màu đỏ hoặc vàng",
            causes = "- Nhện đỏ hai chấm (Tetranychus urticae)\n- Thời tiết nóng khô (trên 27°C)\n- Độ ẩm thấp\n- Thiếu nước\n- Bụi bặm nhiều",
            treatment = "- Phun nước mạnh để cuốn trôi nhện\n- Sử dụng dầu neem hoặc xà phòng côn trùng\n- Phun thuốc trừ nhện (miticide) như Abamectin\n- Thả thiên địch như bọ rùa\n- Tăng độ ẩm không khí",
            prevention = "- Tưới nước đầy đủ, tránh để cây khô hạn\n- Phun nước lên lá định kỳ\n- Loại bỏ cỏ dại xung quanh\n- Trồng cây họ Allium (hành, tỏi) xen kẽ\n- Duy trì độ ẩm không khí cao\n- Kiểm tra cây thường xuyên"
        ),
        
        "Healthy" to DiseaseInfo(
            name = "Lá khỏe mạnh (Healthy)",
            description = "Lá cây khỏe mạnh có màu xanh đều, bề mặt nhẵn không có đốm bệnh hay dấu hiệu của sâu bệnh.",
            symptoms = "- Màu xanh tươi đồng đều\n- Bề mặt lá nhẵn, không có đốm\n- Hình dạng lá bình thường, không cong vênh\n- Không có dấu hiệu của côn trùng\n- Cây phát triển tốt",
            causes = "- Chăm sóc đúng cách\n- Dinh dưỡng đầy đủ và cân đối\n- Tưới nước hợp lý\n- Không có sâu bệnh",
            treatment = "- Không cần điều trị\n- Tiếp tục chăm sóc như hiện tại",
            prevention = "- Duy trì chế độ bón phân đều đặn\n- Tưới nước đầy đủ nhưng không úng\n- Kiểm tra sâu bệnh định kỳ\n- Đảm bảo ánh sáng và thông gió tốt\n- Cắt tỉa hợp lý\n- Phun thuốc phòng bệnh định kỳ"
        ),
        
        "Mosaic_virus" to DiseaseInfo(
            name = "Virus khảm lá (Mosaic Virus)",
            description = "Virus khảm lá gây ra các vệt và đốm màu trên lá, làm giảm khả năng quang hợp và năng suất cây trồng.",
            symptoms = "- Lá có các mảng màu xanh đậm và xanh nhạt xen kẽ\n- Lá biến dạng, cong vênh\n- Cây còi cọc, sinh trưởng kém\n- Quả nhỏ, có đốm màu\n- Hoa ít, ra quả kém",
            causes = "- Virus khảm thuốc lá (TMV) hoặc virus khảm cà chua (ToMV)\n- Lây truyền qua rệp, tuyến trùng\n- Tiếp xúc với dụng cụ bị nhiễm\n- Cây giống nhiễm bệnh\n- Thuốc lá (người hút thuốc chạm vào cây)",
            treatment = "- KHÔNG CÓ THUỐC chữa virus\n- Loại bỏ và tiêu hủy cây bị bệnh ngay\n- Kiểm soát rệp và côn trùng trung gian\n- Khử trùng dụng cụ bằng cồn hoặc tẩy\n- Rửa tay trước khi chạm cây",
            prevention = "- Sử dụng giống kháng virus\n- Khử trùng dụng cụ sau mỗi lần sử dụng\n- Kiểm soát rệp và sâu bệnh\n- Không hút thuốc lá gần vườn cây\n- Rửa tay trước khi làm vườn\n- Loại bỏ cỏ dại - vật chủ của virus\n- Sử dụng cây giống sạch bệnh"
        ),
        
        "Target_Spot" to DiseaseInfo(
            name = "Đốm mục tiêu (Target Spot)",
            description = "Bệnh đốm mục tiêu là bệnh nấm gây hại trên lá, thân và quả, đặc biệt nguy hiểm trong điều kiện ẩm ướt.",
            symptoms = "- Đốm tròn màu nâu có vòng đồng tâm rõ ràng\n- Đốm có viền vàng xung quanh\n- Bệnh xuất hiện trên lá, thân và quả\n- Lá vàng và rụng sớm\n- Quả có đốm lõm màu nâu",
            causes = "- Nấm Corynespora cassiicola\n- Độ ẩm cao (trên 80%)\n- Nhiệt độ ấm (20-30°C)\n- Mưa nhiều, sương đọng\n- Cây trồng quá dày",
            treatment = "- Loại bỏ và tiêu hủy lá bị bệnh\n- Phun fungicide chứa Azoxystrobin, Chlorothalonil\n- Cải thiện thoát nước và thông gió\n- Giảm độ ẩm trong nhà kính\n- Bón phân cân đối",
            prevention = "- Chọn giống kháng bệnh\n- Trồng với khoảng cách hợp lý\n- Tưới nhỏ giọt, tránh tưới lên lá\n- Phủ mulch để tránh nước bắn\n- Tỉa cành để tăng thông thoáng\n- Luân canh 2-3 năm\n- Phun thuốc phòng bệnh trước mùa mưa"
        )
    )
    
    fun getDiseaseInfo(diseaseName: String): DiseaseInfo {
        return diseaseMap[diseaseName] ?: DiseaseInfo(
            name = "Không xác định",
            description = "Không tìm thấy thông tin về bệnh này.",
            symptoms = "Không có thông tin",
            causes = "Không có thông tin",
            treatment = "Vui lòng tham khảo ý kiến chuyên gia nông nghiệp.",
            prevention = "Vui lòng tham khảo ý kiến chuyên gia nông nghiệp."
        )
    }
}
