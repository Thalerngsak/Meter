package com.pwa.common.master;

public class Comment {
	public static final String[] comments = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09","10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
		"23","24", "25", "26", "27", "28", "29", "30", "99","999" };
public static final  String[] commentDesc = { "ปกติ", "บ้านปิด มีคนอยู่อาศัย", "มีสิ่งกีดขวาง","มีสัตว์ดุร้าย","บ้านว่าง", "ลวดตีตรามาตรขาด","ไม่มีลวดตีตรามาตร",
                                        "หักผ่านธนาคาร", "ตัวเลขขึ้นแบบก้าวกระโดด", "หลังมาตรมีการใช้ปั้มสูบน้ำ","มีการลักใช้น้ำ","ยูเนี่ยนมาตรรั่ว",
	"ท่อเมนรองรั่ว", "ประตูน้ำรั่ว", "หน้าปัดแตก แต่าอ่านตัวเลขได้", "มาตรตายตัวเลขไม่หมุน", "มาตรอยู่ในดินหรือซีเมนต์ อ่านตัวเลขได้",
                                        "มาตรจมน้ำ แต่อ่านตัวเลขได้","มาตรครบรอบ",
	 "ตัวเลขที่อ่านได้น้อยกว่าครั้งก่อน", "มีการติดมาตรสลับบ้าน", "มีการติดมาตรกลับด้าน", "มาตรเดินถอยหลัง", "มาตรมองไม่เห็นตัวเลขหน่วยน้ำ", "มาตรเดินเร็วผิดปกติ",
	"ตัวเลขขึ้นแบบก้าวกระโดด", "ใช้น้ำน้อยผิดปกติ", "ใช้น้ำมากผิดปกติ","มาตรหาย","ใช้น้ำน้อยผิดปกติมาก", "ใช้น้ำมากผิดปกติมาก", "อื่นๆ","ยังไม่อ่าน" ,};

	public static String getCommentDesc(String comment) {
		String r =  "อื่นๆ";
		for (int i = 0; i < comments.length; i++) {
			if (comments[i].equals(comment)) {
				r = commentDesc[i];
				break;
			}
		}
		return r;
	}

}