public static void createArmy(){
    Unit[] units = new Unit[5];
    for(int i = 0; i < 5; i++){
        units[i] = new Unit("Unit " + (i+1));
    }

    Knight[] knights = new Knight[3];
    for(int i = 0; i < 3; i++){
        knights[i] = new Knight("Knight " + (i+1));
    }

    General general = new General("General Kenobi");

    Doctor doctor = new Doctor("Doctor Who");
}