package hw2;
import java.util.ArrayList;

public class RegisterBank {
    private ArrayList<Word> rs;
    
    public RegisterBank(int size, int bits){
        rs = new ArrayList<Word>();
        for(int i = 0; i < size; i++){
            rs.add(new Word(bits));
        }
    }
    public Word get(int index){
        int[] i = rs.get(index).get();
        Word r = new Word(i.length);
        r.set(i);
        return r;
    }
    public void set(int index, Word r){
        rs.get(index).set(r.get());
    }
}
