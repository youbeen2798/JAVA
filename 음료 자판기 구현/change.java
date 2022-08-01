import java.util.*;

public class change {
    
    public static Map Change(int money){ // 거스름돈을 동전으로 거슬러 주는 메소드(그리디 알고리즘 사용)
        
        int coins[] = {500,100,50,10};

        Map<Integer,Integer> map = new HashMap<Integer,Integer>();

        for(int coin : coins){
            int cnt = money/coin;
            if(cnt > 0){
                map.put(coin,cnt);
                money -= coin *cnt;
            }
        }

        Set<Integer> keySet = map.keySet();
        for(Integer key : keySet){
            System.out.print(key + "원 동전 " + map.get(key) + "개 ");
        }
        System.out.println("를 거슬러 받으십시오.");

        return map;
    }
}
