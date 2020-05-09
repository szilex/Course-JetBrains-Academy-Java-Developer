import java.util.*;

interface Movable {
    int getX();
    int getY();
    void setX(int newX);
    void setY(int newY);
}

interface Storable {
    int getInventoryLength();
    String getInventoryItem(int index);
    void setInventoryItem(int index, String item);
}

interface Command {
    void execute();
    void undo();
}

class CommandMove implements Command {
    Movable entity;
    int xMovement;
    int yMovement;

    private List<Integer> xMoves = new ArrayList<>();
    private List<Integer> yMoves = new ArrayList<>();

    @Override
    public void execute() {
        entity.setX(entity.getX() + xMovement);
        xMoves.add(xMovement);

        entity.setY(entity.getY() + yMovement);
        yMoves.add(yMovement);
    }

    @Override
    public void undo() {
        for (Integer xMove : xMoves) {
            entity.setX(entity.getX() - xMove);
        }
        xMoves.clear();

        for (Integer yMove : yMoves) {
            entity.setY(entity.getY() - yMove);
        }
        yMoves.clear();
    }
}

class CommandPutItem implements Command {
    Storable entity;
    String item;

    //private ArrayList<Integer> indexesOfAddedItems = new ArrayList<>();
    private Integer index;

    @Override
    public void execute() {
        int inventoryLength = entity.getInventoryLength();
        for (int i = 0; i < inventoryLength; i++) {
            if (entity.getInventoryItem(i) == null) {
                entity.setInventoryItem(i, this.item);
                //indexesOfAddedItems.add(i);
                index = i;
                break;
            }
        }
    }

    @Override
    public void undo() {
        /*for (int index : indexesOfAddedItems) {
            entity.setInventoryItem(index, null);
        }
        indexesOfAddedItems.clear();*/

        if (index != null) {
            entity.setInventoryItem(index, null);
        }
    }
}