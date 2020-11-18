package ru.mirea.prak_16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class Loader {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выбор решения:" + "\n" + "1.Регулярные выражения" + "\n" + "2.Без регулярных выражений");
        int numb = scanner.nextInt();

        if(numb == 1){
            int numberRules = 0;
            System.out.print("Введите количество правил: ");
            try{
                numberRules = scanner.nextInt();
            } catch (NumberFormatException e){
                e.printStackTrace();
            }
            String path;
            String rep;
            Map<String, String> rule = new HashMap<>();
            for (int i = 0; i <= numberRules; i++) {
                Pattern pattern = Pattern.compile("(?<path>\\w+) (?<rep>\\w+)");
                Matcher matcher = pattern.matcher(scanner.nextLine());
                if (matcher.find()) {
                    path = matcher.group("path");
                    rep = matcher.group("rep");
                    rule.put(path, rep);
                }
            }

            String text = scanner.nextLine();
            List<Map.Entry<String, String>> ruleConvert = new ArrayList<>(rule.entrySet());
            StringBuilder regex = new StringBuilder();
            for (int i = 0; i < ruleConvert.size(); i++) {
                regex.append(ruleConvert.get(i).getKey());
                if (i < ruleConvert.size()-1) {
                    regex.append("|");
                }
            }
            Pattern onePattern = Pattern.compile(regex.toString());
            Matcher oneMatcher = onePattern.matcher(text);
            String result = oneMatcher.replaceAll(index -> rule.get(index.group()));
            System.out.println("result: " + result);
        }
        else if(numb == 2){
            int numberRules = 0;
            System.out.print("Введите количество правил: ");
            try {
                numberRules = scanner.nextInt();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            Map<String, String> rule = new HashMap<>();
            for (int i = 0; i < numberRules; i++) {
                rule.put(scanner.next(), scanner.next());
            }
            String text = scanner.next();
            String result = text;
            for (int i = 0; i < text.length(); i++) {
                for (int j = 0; j < rule.size(); j++) {
                    String t = (String) rule.keySet().toArray()[j];
                    String r = (String) rule.values().toArray()[j];
                    if (i + t.length() < result.length() && result.substring(i, i + t.length()).equals(t)) {
                        result = result.replace(t, r + " ");
                        i += r.length();
                        break;
                    }
                }
            }
            result = result.replace(" ", "");
            System.out.println(result);
        }
    }
}
