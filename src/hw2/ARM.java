
package hw2;

public class ARM {
    public RegisterBank rb;
    public RAM mb;
    public int sp = 8*32;
    public int pc = 0;
    private final int bits;
    private final int registers;
    private final int size_ram;
    
    public ARM(int size_reg, int bits_reg, int size_ram, int bytes_ram){
        rb = new RegisterBank(size_reg,bits_reg);
        mb = new RAM(size_ram,bytes_ram);
        registers=size_reg;
        bits = bits_reg;
        this.size_ram = size_ram;
    }
    /*
    Where Rd is register and Rn is a value in bits, and a shift in bits,    
    */
    public void mov(int Rd, int Rn, int bitshift){
        System.out.println("MOV...");
        System.out.println("Value of Rd: r" + Rd);
        System.out.println("Value of Rn: #" + Rn);
        System.out.println("Value of bitshift: " + bitshift);
        /*
        PT 1. INITIALIZING BITS!!!!
            |=======|    //|  |
            |   _   |   // |  |
            |  | |  |      |  |
            |  |_|  |   ___|  |___
            |_______|   |________|
        */
        //Both variables used to store into register
        Word Rn1 = new Word(bits);
        int[] RntoBits  = numtoBits(Rn1,Rn);
        //Sets value of word to Rn in bit form
        Rn1.set(RntoBits);
        //Set the register value into Rd for now....
        rb.set(Rd,Rn1);
        /*
        PT 2.  SHIFT BITS!!!!!!!
            |       ___ |---->
            |     /  /  |---->
            |   /___/   |---->
            |_____ _____|---->
            Rn1 contains the value of Rn currently, but it isn't shifted and
            placed in register.
        */
        //new Word set up
        Word shiftBit = new Word(bits);
        //Calls method to shiftBits
       RntoBits = shiftBits(Rn1,bitshift);
       //Finally sets shiftBit value to array and sets registry
       shiftBit.set(RntoBits);
       rb.set(Rd, shiftBit);
       pc++; //Increment Program Counter
    }
    
    //Store Rd into Rn with an offset ammount, and pre, !
    public void str(int Rd, int Rn, int o, boolean pre, boolean mod_pre){
        //Setting up Rn1 to have Rn value in bits
        Word Rn1 = new Word(bits);
        Rn1.set(rb.get(Rd).get());
        
        
        //
        if(pre){//if a pre-Offset
            if(mod_pre == false){//If no exclamation point
                sp += (o*8);//sp + offset in bits
                mb.set(Rn1, sp + (Rd * bits)); //set RAM to have value in it at position
                sp -= (o*8);
            }
            else{
                sp += (o*8);//sp + offset in bits
                mb.set(Rn1, sp + (Rd * bits));
            }
        }
        else{//If a post-Offset
            if(mod_pre == false){
                mb.set(Rn1, sp + (Rd * bits));
                sp += (o*8);//sp + offset in bits
            }
            else{
                throw new NullPointerException();
            }

        }        
        pc++;//program counter++
    }
                      //reg,   sp,   offset,    pre,   !
    public void ldr(int Rd, int Rn, int o, boolean pre, boolean mod_pre){
        //For Loop that will group each amount of bits to each register.
        if(pre){//If it is a pre-offset
            if(mod_pre){
                sp+= (o*8);
                rb.set(Rd, mb.get(bits / 8, sp + (Rd*bits)));
            }
            else{
                sp+= (o*8);
                rb.set(Rd, mb.get(bits / 8, sp + (Rd*bits)));
                sp-= (o*8);
            }
        }
        else{ //If it is a post offset
            sp+= (o*8);
            rb.set(Rd, mb.get(bits / 8, sp + (Rd*bits)));
        }
        pc++;//Program counter increment
        
    }
    
    public void add(int Rd, int Rn, int Rc){
        int[] RnBits = rb.get(Rn).get();
        int[] RcBits = rb.get(Rc).get();
        int[] RnDest = new int[RnBits.length];
        Word w = new Word(bits);
        //Loop that doesn't carry...
        for(int i = 0; i < RnBits.length; i++){
            RnDest[i] = RnBits[i] + RcBits[i];
        }
        
        //Loop that carries over numbers
        for(int i = RnDest.length-1; i >=0; i--){
            if(RnDest[i] > 1){
                RnDest[i]-=2;
                try{
                RnDest[i-1]+=1;
                }
                catch(Exception e){
                    
                }
            }
            w.set(RnDest);
            rb.set(Rd, w);
            
        }
        pc++;
    }
    
    public void print(){
        int track = 0;
        int[] val;        
        try{
            for(int i = 0; i <= registers; i++){
                val = rb.get(track).get();
                System.out.print("Register " + track + ":  ");
                for(int j = 0; j < bits;j++){
                    System.out.print(val[j]);
                }
                System.out.println();
                
                track++;
                }
        }
        catch(IndexOutOfBoundsException e){
        }
        
        System.out.println("SP:  " + sp);
        System.out.println("PC:  " + pc);
      
       
    }
    
    
    /*
    Converts number to Bits...
    Does NOT shift...
    */
    public int[] numtoBits(Word w, int Rn){
        int[] RntoBits  = new int[w.bits];
        //Used to convert to binary value
        String s = Long.toBinaryString(Rn);
        
        //Loop that allows for binary representation
        for(int i = 0; i< s.length(); i++){
            //Sets a reverse value to end of array
            RntoBits[RntoBits.length-1-i]= Integer.valueOf(s.substring(s.length()-1-i, s.length()-1-(i-1)));
        }
        
                
        
        return RntoBits;
    }
    
    /*
    Shifts the bits of a Word....
    */
    
    public int[] shiftBits(Word w, int bitshift){
        int[] RntoBits = w.get();
        
        /*
        Shifting left!
        */
       if(Integer.toString(bitshift).contains("-")){ //SHIFTING LEFT...
           //Count through each shift
           for(int i = 0; i < Math.abs(bitshift); i++){
               //Loop to rotate each time until it reaches [1]
               int temp = RntoBits[0];//Value stored
               for(int j = 0; j < RntoBits.length-1; j++){
                    try{
                        RntoBits[j]=
                        RntoBits[j+1]; //take whatever is left of j
                    }
                    catch(Exception e){
                        System.out.println("ERROR");
                        temp = RntoBits[0];
                            RntoBits[RntoBits.length-1] = temp;
                            RntoBits[j]=RntoBits[j+1];
                        
                    }
               }
               RntoBits[RntoBits.length-1]= temp;               
           }
       }
       /*
       Shifting Right!
       */
       else{ //SHIFTING RIGHT...
            //Count through each shift
           for(int i = 0; i < bitshift; i++){
               //Loop to rotate each time until it reaches [1]
               int temp = RntoBits[RntoBits.length-1];//Value stored
               for(int j = RntoBits.length-2; j >= 0; j--){
                    try{
                        RntoBits[j+1]=
                        RntoBits[j]; //take whatever is left of j
                    }
                    catch(Exception e){ //CATCHES OUT OF BOUNDS
                        temp = RntoBits[RntoBits.length-1];
                        RntoBits[0] = temp;
                        RntoBits[j]=RntoBits[j-1];
                    }
                }
                RntoBits[0]= temp;
            }
       }
       
       return RntoBits;
    }
    
}
