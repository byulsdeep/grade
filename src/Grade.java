
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Grade {

	public static void main(String[] args) {
		new App();
	}
}

class App {

	private final Scanner sc;
	private final List<Kamoku> kamokuList;

	public App() {
		this.sc = new Scanner(System.in);
		this.kamokuList = new ArrayList<>();
		this.run();
		this.sc.close();
	}

	private void run() {

		String input;

		while (true) {
			this.println("学生成績登録システム");
			this.println("1.登録2.終了");
			input = this.sc.nextLine().trim();
			if (input.equals("1")) {
				this.register();
			} else if (input.equals("2")) {
				break;
			} else {
				this.println("他の番号を選んでください。");
			}
		}
		
		// 結果画面作成
		
		StringBuilder sb = new StringBuilder();
		Map<String, Set<String>> subjectsPerStudent = new HashMap<>();	
		Set<String> subjects;
		
		sb.append(!this.kamokuList.isEmpty() ? "登録内容\n" : "");
		
		// 名前、科目分類　＋　List<Kamoku>出力
		for (Kamoku kamoku : this.kamokuList) {
			subjects = subjectsPerStudent.getOrDefault(kamoku.getNamae(), new HashSet<>());
			subjects.add(kamoku.getKamokumei());
			subjectsPerStudent.put(kamoku.getNamae(), subjects);
			sb.append(kamoku.getNamae());
			sb.append("さん、");
			sb.append(kamoku.getKamokumei());
			sb.append("の点数");
			sb.append(kamoku.getKamokuTennsuu());
			sb.append("\n");
		}
		
		// 名前、科目分類結果出力
		for (Map.Entry<String, Set<String>> entry : subjectsPerStudent.entrySet()) {
			sb.append("なまえ");
			sb.append(entry.getKey());
			sb.append("さんがうけている科目は");
			for (String subject : entry.getValue()) {
				sb.append(subject);
				sb.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("です\n");
		}
		
		System.out.print(sb.toString());
		System.out.println("終了します");
	}

	private void register() {
		Kamoku kamoku;
		String namae;
		String input;
		int inputParsed; 
		
		this.println("名前は：");
		namae = this.sc.nextLine().trim();
		
		while (true) {		
			kamoku = new Kamoku();
			kamoku.setNamae(namae);
			this.println(kamoku.getNamae() + "さんの登録する科目は？＊終了は０番");
			input = this.sc.nextLine().trim();
			if (input.equals("0")) break;
			kamoku.setKamokumei(input);
			this.println(kamoku.getKamokumei() + "の点数は？");
			
			while (true) {
				input = this.sc.nextLine().trim();
				try {
					inputParsed = Integer.parseInt(input);
				} catch (Exception e) {
					this.println("数字を入力してください");
					continue;
				}
				kamoku.setKamokuTennsuu(inputParsed);
				break;
			}
			this.kamokuList.add(kamoku);
		}
	}

	private void println(String s) {
		System.out.println(s);
	}
}
