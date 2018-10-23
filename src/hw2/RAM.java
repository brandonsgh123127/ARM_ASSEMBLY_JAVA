package hw2;

/*
*/

public class RAM {
    private int[] rs;
    
    public RAM(int size, int bytes){
        rs = new int[size*8*bytes];  //size * bits
    }
    
    //converts to bits and then creates a new word, which shifts by amount
    public Word get(int bytes, int a){
        int bits = 8*bytes; //convert to bits
        //creates new array, with bit size
        int[] r = new int[bits];
        
        //Loop to set a shift in array for values
        for(int i = 0; i < bits; i++){
            r[i] = rs[a+i];
        }
        Word w = new Word(bits);
        w.set(r);
        return w;       
    }
    
    //shifts by a, in bits
    public void set(Word w, int a){
        int[] r = w.get();
        for(int i = 0; i < r.length; i++){
            rs[a+i] = r[i];
        }
    }
}
