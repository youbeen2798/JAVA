import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Delete extends Game{

    public static Unit[] DeleteNull(Unit unit[]){
        
        // 객체 배열 중 null인 값(체력이 0이하인 값)을 제거하기 위한 코드
        List<Unit> list = new ArrayList(Arrays.asList(unit));    //배열을 리스트로 만들어     
        list.removeAll(Collections.singletonList(null));     // 원소 삭제 후
        int listSize = list.size(); 
        unit = list.toArray(new Unit[listSize]); //다시 리스트를 배열로 치환

        return unit;
    }
     
    
}
