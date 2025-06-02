package EK;

import java.util.List;
import java.util.Scanner;

import DAO.QuotationDao;
import utils.GetFileUtil;

public class QuotationDemo {

	public static int getValidIntegerInput(Scanner scanner, String prompt) {
		while (true) {
			System.out.print(prompt);
			String input = scanner.nextLine().trim();
			
			if (input.isEmpty()) {
				System.out.println("❌ 輸入不可為空，請重新輸入！");
				continue;
			}
			
			try {
				int value = Integer.parseInt(input);
				if (value > 0) {
					return value; // ✅ 成功回傳
				} else {
					System.out.println("❌ 請輸入大於 0 的整數！");
				}
			} catch (NumberFormatException e) {
				System.out.println("❌ 請輸入正確的整數格式！");
			}
		}
	}
	public static String getNonEmptyString(Scanner scanner, String prompt) {
	    while (true) {
	        System.out.print(prompt);
	        String input = scanner.nextLine().trim();

	        if (!input.isEmpty()) {
	            return input;
	        } else {
	            System.out.println("❌ 輸入不可為空，請重新輸入！");
	        }
	    }
	}
	public static void main(String[] args) {
		
			

		// 轉出cmd
		System.out.println("==輪胎價目表==");
		Scanner scanner = new Scanner(System.in);
		QuotationDao quotationDao = new QuotationDao();
		List<String> data = GetFileUtil.getData("C:\\progarm\\price.csv");
		while (true) {
			System.out.println("==輪胎價目表==");
			System.out.println("A.查詢全部");
			System.out.println("B.單筆查詢");
			System.out.println("C.新增");
			System.out.println("D.修改");
			System.out.println("E.刪除");
			System.out.println("F.輸出json");
			System.out.println("G.離開程式");
			System.out.flush();

			String button = scanner.nextLine().trim().toUpperCase();

			switch (button) {
			case "A":
				List<Quotation> all = quotationDao.findAllProduct();
				for (Quotation quotation : all) {
					System.out.println(quotation);
				}
				System.out.println("資料筆數: " + all.size());
				break;
			case "B":
				System.out.print("請輸入流水號:");
				Integer number = Integer.parseInt(scanner.nextLine());
				quotationDao.cheackNumber(number);
				break;
			case "C":
				System.out.println("請輸入欲新增之流水號:");
				Integer newNumber = scanner.nextInt();
				scanner.nextLine();
				System.out.println("請輸入用途");
				String useway = scanner.nextLine();
				System.out.println("請輸入品牌");
				String brand = scanner.nextLine();
				System.out.println("請輸入尺寸");
				String size = scanner.nextLine();
				System.out.println("請輸入FR");
				String fr = scanner.nextLine();
				System.out.println("請輸入載重與速度");
				String loadAndSpeed = scanner.nextLine();
				System.out.println("請輸入價格");
				Integer price = scanner.nextInt();
				scanner.nextLine();
				System.out.println("請輸入備註");
				String note = scanner.nextLine();
				Quotation scannerNewProduct = new Quotation(newNumber, useway, brand, size, fr, loadAndSpeed, price,
						note);
				quotationDao.saveQuotation(scannerNewProduct);
				break;
			case "D":
				int numberchang = getValidIntegerInput(scanner, "請輸入要修改的流水號");
				while (true) {
					System.out.println("請選擇要修改的欄位:");
					System.out.println("1.用途");
					System.out.println("2.品牌");
					System.out.println("3.尺寸");
					System.out.println("4.FR");
					System.out.println("5.載重與速度");
					System.out.println("6.建議售價");
					System.out.println("7.備註");

					String choice = scanner.nextLine().trim();

					switch (choice) {
					case "1":
						String useway1 = getNonEmptyString(scanner, "請輸入新的用途:");
						quotationDao.changeProduct(numberchang, "useway", useway1);
						break;
					case "2":
						String brand2 = getNonEmptyString(scanner, "請輸入新的品牌:");
						quotationDao.changeProduct(numberchang, "brand", brand2);
						break;
					case "3":
						String size3 = getNonEmptyString(scanner, "請輸入新的size:");
						quotationDao.changeProduct(numberchang, "size", size3);
						break;
					case "4":
						String fr4 = getNonEmptyString(scanner, "請輸入新的FR:");
						quotationDao.changeProduct(numberchang, "FR", fr4);
						break;
					case "5":
						String loadAndSpeed5 = getNonEmptyString(scanner, "請輸入新的載重與速度:");
						quotationDao.changeProduct(numberchang, "loadAndSpeed", loadAndSpeed5);
						break;
					case "6":
						String price6 = getNonEmptyString(scanner, "請輸入新的建議售價:");
						quotationDao.changeProduct(numberchang, "price", price6);
						break;
					case "7":
						String note7 = getNonEmptyString(scanner, "請輸入新的備註:");
						quotationDao.changeProduct(numberchang, "note", note7);
						break;
					case "8":
						System.out.println("退出修改模式");
						break;
					default:
						System.out.println("請輸入1~5選項!");

					}
				}

			case "E":
				System.out.println("請輸入欲刪除商品之流水號");
				int numbers = Integer.parseInt(scanner.nextLine());
				quotationDao.deleteQuotationByNumber(numbers);
				break;
			case "F":
				System.out.println("輸出json");
				List<Quotation> quotation = quotationDao.findAllProduct();
				GetFileUtil.exportToJson(quotation, "C:\\Users\\tina8\\Desktop\\product.json");
				System.out.println("資料筆數: " + quotation.size());
				break;
			case "G":
				System.out.println("離開程式");
				return;
			default:
				System.out.println("無效選項！");
			}

		}

	}

}
