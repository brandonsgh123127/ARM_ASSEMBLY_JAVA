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
        //10 registers, 4 bytes, 15 * 32 size ram, 4 bytes
        ARM a = new ARM(8,32,15 * 32,4);
        
        //r0,r1,r2,r5 have values
        a.mov(0, 0x00000001, 0);//mov 1 into r0
        
        a.mov(1, 0x00000015, 4); //Moved 21 into r1 with a 4 bit shift
        //00000000000000000000000000010101 --> 00000000000000000000000000010101
        System.out.println("\n");
        a.mov(2,0x00000F10,2);//Moved 3856 into r2
        System.out.println("\n");
        
        a.mov(5, 0x00000011, -30); // 17 into r5
        System.out.println("\n");
        
        System.out.println("THIS IS BEFORE STORING/LOADING \n"
                            + "But We Have Stored Into r0, r1,r2,r5");
        a.print();
        System.out.println("\n");
        
                                                
        a.str(0, a.sp, 12, false, false);//Post-offset, stores in current sp, 
                                            //then changes sp  
                                            //value 1
        //
        a.str(0,a.sp,-12,true,true);//pre-offset!!!!!!  Sp has offset of -12*8
                                            //value 0

        //
        a.mov(0, 14, -3);//Moves 14 with a bitshift of LSL 3 into R0
        System.out.println("THIS IS AFTER MOVING R0");
        a.print();
        
        a.str(0,a.sp,0,true,true); //Pre-offset!!  Sp stays the same
                                            //value 14 LSL 3
        a.str(2,a.sp,12,true,false);  //Pre-offset, moves sp 12*8, but goes back
                                            //value 3856
        a.mov(2, 4, 0);//no bitshift
        System.out.println("THIS IS AFTER MOVING R2, VALUE 4:  ");
        a.print();
        
        a.ldr(2, a.sp, 12, true, false);  //LOAD R2, which is 3856
        System.out.println("THIS IS AFTER LOADING R2, WHICH IS 3856:  ");
        a.print();
        a.mov(0, 2563, 3);
        System.out.println("THIS IS AFTER MOVING R0, WHICH IS 2563 LSL 3:  ");
        a.print();
        
        a.ldr(0, a.sp, 0, true, true);  //LOAD R0, which is 14LSL3
        
        System.out.println("THIS IS AFTER LOADING R0, WHICH IS 14 LSL 3:  ");
        a.print();
        
        
        /*
        
        NEED TO GET STR TO WORK....  HOW DO I DO MULTIPLE REGISTER BANKS
        */ 

    }
    
}
