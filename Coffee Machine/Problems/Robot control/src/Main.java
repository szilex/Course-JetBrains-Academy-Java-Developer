public static void moveRobot(Robot robot, int toX, int toY) {

    int shiftX = toX - robot.getX();
    int shiftY = toY - robot.getY();

    if(shiftX > 0) {
        switch(robot.getDirection()) {
            case UP:
                robot.turnRight();
                break;
            case RIGHT:
                break;
            case DOWN:
                robot.turnLeft();
                break;
            case LEFT:
                robot.turnRight();
                robot.turnRight();
                break;
        }
    }
    else if(shiftX < 0) {
        switch(robot.getDirection()) {
            case DOWN:
                robot.turnRight();
                break;
            case LEFT:
                break;
            case UP:
                robot.turnLeft();
                break;
            case RIGHT:
                robot.turnRight();
                robot.turnRight();
                break;
        }
    }

    for(shiftX = Math.abs(shiftX); shiftX>0; shiftX--){
        robot.stepForward();
    }

    if(shiftY > 0) {
        switch(robot.getDirection()) {
            case LEFT:
                robot.turnRight();
                break;
            case UP:
                break;
            case RIGHT:
                robot.turnLeft();
                break;
            case DOWN:
                robot.turnRight();
                robot.turnRight();
                break;
        }
    } else {
        switch(robot.getDirection()) {
            case RIGHT:
                robot.turnRight();
                break;
            case DOWN:
                break;
            case LEFT:
                robot.turnLeft();
                break;
            case UP:
                robot.turnRight();
                robot.turnRight();
                break;
        }
    }

    for(shiftY = Math.abs(shiftY); shiftY>0; shiftY--){
        robot.stepForward();
    }
}