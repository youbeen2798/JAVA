//프로그램 설명
//해당 소스에는 자바의 54개의 키워드가 존재함(ex. abstract, char...)
//자바 소스파일을 명령줄 인자로 받아 자바 소스파일에서 사용된 키워드의 개수를 출력하는 프로그램


//Hello.java 파일 예시
/*
public class Hello {
    public static void main(String[] args){
        System.out.println("Hello World");
    }
}
*/

//입력 예시
// java KeyWordCount Hello.java

// 출력 예시

// public : 2
// class : 1
// static : 1


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class KeyWordCount{

    public static String[] words= {
        "abstract", "assert", "boolean", "break", "byte", "case", "catch",
        "char", "class", "const", "continue", "default", "do", "double", "else", "enum", "extends", "false", "final", "finally",
        "float", "for", "If", "goto", "Implements", "Import", "Instanceof", "Int", "interface", "long", "native", "new", "null",
        "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronize", "this",
        "throw", "thrwos", "transient", "true", "try", "void", "volatile", "while"};


    //set으로 선언한 이유 : set은 값의 유무(검색)를 빨리 판단할 수 있기 때문에, 시간적 효율성이 존재함.
    //아래 문자열들은 값이 겹칠 일이 없기 때문에, set(집합)으로 선언해도 무방함
    //만약 파일의 크기가 커진다면, 배열과 set의 값 유무 확인의 시간 차는 매우 클 것임
    public static Set<String> set = Arrays.stream(words).collect(Collectors.toSet());

    //첫번째 인자값: 해당 파일에 나온 키워드, 두번째 인자값 : 반복된 횟수
    public static Map<String, Long> map = new HashMap<String, Long>();

    public static void main(String[] args){

        //파일의 알파벳으로 구성된 문자열을 하나씩 담을 리스트(ex. apple, banana...)
        ArrayList<String> list = new ArrayList<String>();

        //입력 받은 파일이 있는지 검사
        if(args.length == 0){
            System.out.println("Please enter the file name");
            System.exit(0);
        }

        //입력받은 파일이 존재하는지 검사
        String fileName = args[0];
        File file = new File(fileName);

        Charset charset = StandardCharsets.UTF_8;


        FileInputStream stream = null;
        try{             
            //파일 스트림으로 파일 읽어오기
             stream = new FileInputStream(file);


            //파일이 너무 커서 단일 읽기 읽기 작업으로 읽을 수 없는 경우를 대비
            //입력 시스템에서 바이트 어레이로 지정된 길이 바이트의 데이터를 읽음으로써
            // 전체 파일을 한 번에 바이트 어레이로 읽을 수 있음
            // 이후 바이트는 string 생성자를 이용하여 -> 문자 집합이 있는 문자로 디코딩됨
            byte[] bytes = new byte[(int) file.length()]; 
            stream.read(bytes); 
 
            String fileContent = new String(bytes, charset); //바이트들의 집합 -> 문자열에 저장
            char[] chs = fileContent.toCharArray(); //문자열 -> 문자 배열로 저장
            
            String s = "";
            for(int i=0; i<chs.length; i++){ //문자 배열의 원소를 하나씩 비교하며
                if(('a' <= chs[i] && chs[i] <= 'z')  || ('A' <= chs[i] && chs[i] <= 'Z')){ //만약 배열의 원소가 알파벳이라면
                    s = s + chs[i]; //문자열에 문자 추가
                }
                else{
                    if(s.length() != 0){ //만약 문자열이 null값이 아니라면
                        list.add(s);
//                        System.out.println("문자열: " + s);
                        s = "";
                    }
                    continue; // 문자 배열의 원소가 연속으로 알파벳이 아닐 경우, 문자열 s는 공백임
                }
                
            }

            String[] strArr = list.toArray(new String[list.size()]); //문자열 리스트를 문자열 배열로 바꿔줌

        
            for(int i=0; i<strArr.length; i++){
                if(set.contains(strArr[i])){
                    if(map.containsKey(strArr[i])){
                        map.put(strArr[i], map.get(strArr[i]) + 1);
                    }
                    else{
                        map.put(strArr[i], (long) 1);
                    }
                }
            }
       
            map.forEach((key, value) ->{
            System.out.println(key + " : " + value);
            });

            try{
                stream.close();
            }
            catch(IOException e){ // 파일을 열어 정상적으로 읽었으나 stream을 닫을 때 에러 발생의 경우
                e.printStackTrace();
            }

        }
        catch(FileNotFoundException e){ //파일을 찾지 못할 경우
            e.printStackTrace();
        }
        catch(IOException e){ //파일을 찾았으나 읽지 못했을 경우
            try{
                if(stream != null){
                    stream.close();
                }
            }
            catch(IOException e2){ // 파일을 찾았으나 읽지 못하여 filestream을 닫으려고 했으나 닫지 못했을 경우
                e.printStackTrace();
            }
        }

        
    }
}
