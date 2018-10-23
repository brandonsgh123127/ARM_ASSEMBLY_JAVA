package hw2;

public class HW2 {

    public static void main(String[] args) {
        /*  (1) write a short program using the java ARM class that is like this C++ code:
            int x = 13;
            int y = 14;
            int z = 16;
            z = x+y+z;
        Note: assume 32bit ARM and RAM should have 4 byte alignments (4*bytes)
            (2) call the print method periodically to check your work or use the debugger
        */
        //10 registers, 4 bytes, 32 size ram, 4 bytes
        ARM a = new ARM(10,32,32,4);
        
        a.mov(1, 0x00000015, 4); //Moved 21 into r1 with a 4 bit shift
        //00000000000000000000000000010101 --> 00000000000000000000000000010101
        System.out.println("\n");
        a.mov(2,0x00000F10,2);//Moved 3856 into r2
        System.out.println("\n");
        
        a.mov(5, 0x00000011, -30); //
        System.out.println("\n");
        
        
        
        
        

    }
    
}
