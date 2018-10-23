package hw2;

public class Word {
    private int[] bs;  //SET BACK TO PRIVATE AFTER...
    public int bits;
    
    public Word(int bits){ //sets a new array the size of the bits
        bs = new int[bits];
        this.bits = bits;
    }
    
    //Creates an array and populates, then returns array values
    public int[] get(){
        int[] r = new int[this.bs.length];
        for(int i = 0; i < this.bs.length; i++){
            r[i] = this.bs[i];
        }
        return r;
    }
    
    //setting the value of the word array
    public void set(int[] r){
        for(int i = 0; i < r.length; i++){
            this.bs[i] = r[i];
        }
    }  
}
