package machine;

import java.util.*;

public class CoffeeMachine {

    private int water = 400;
    private int milk = 540;
    private int beans = 120;
    private int cups = 9;
    private int money = 550;
    private Action action = Action.MENU;
    private Coffee coffee;
    private Ingredient ingredient = Ingredient.WATER;

    enum Action{
        MENU,
        BUY,
        FILL,
        TAKE,
        REMAINING,
        EXIT
    }

    enum Coffee{
        ESPRESSO,
        LATTE,
        CAPPUCCINO,
        BACK
    }

    enum Ingredient {
        WATER,
        MILK,
        BEANS,
        CUPS
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        while(coffeeMachine.isOperating()){
            System.out.println("Write action (buy, fill, take, remaining, exit) :");
            String message = scanner.next();
            coffeeMachine.setAction(message);
            switch (message) {
                case "buy":
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    String coffeeType = scanner.next();
                    coffeeMachine.setAction(coffeeType);
                    break;
                case "fill":
                    String amount;

                    System.out.println("Write how many ml of water do you want to add:");
                    amount = scanner.next();
                    coffeeMachine.setAction(amount);

                    System.out.println("Write how many ml of milk do you want to add:");
                    amount = scanner.next();
                    coffeeMachine.setAction(amount);

                    System.out.println("Write how many grams of coffee beans do you want to add:");
                    amount = scanner.next();
                    coffeeMachine.setAction(amount);

                    System.out.println("Write how many disposable cups of coffee do you want to add:");
                    amount = scanner.next();
                    coffeeMachine.setAction(amount);

                    break;
                case "take":
                    coffeeMachine.takeMoney();
                    break;
                case "remaining":
                    coffeeMachine.printAmounts();
                    break;
                case "exit":
                    break;
            }
        }
    }

    public void setAction(String message){
        if(this.action==Action.MENU){
            switch (message) {
                case "buy":
                    this.action = Action.BUY;
                    break;
                case "fill":
                    this.action = Action.FILL;
                    break;
                case "take":
                    this.action = Action.TAKE;
                    break;
                case "remaining":
                    this.action = Action.REMAINING;
                    break;
                case "exit":
                    this.action = Action.EXIT;
                    break;
            }
        } else if(action==Action.BUY){
            switch(message) {
                case "1":
                    this.coffee = Coffee.ESPRESSO;
                    break;
                case "2":
                    this.coffee = Coffee.LATTE;
                    break;
                case "3":
                    this.coffee = Coffee.CAPPUCCINO;
                    break;
                case "back":
                    this.coffee = Coffee.BACK;
            }
            buyCoffee();
        } else if(action==Action.FILL) {
            fillIngredient(Integer.parseInt(message));
        }

    }

    public boolean isOperating(){
        if(action==Action.EXIT){
            return false;
        }
        return true;
    }

    private void printAmounts(){
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(beans + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println(money + " of money");
        action = Action.MENU;
    }

    private void buyCoffee(){
        int waterUsed = 0, milkUsed = 0, beansUsed = 0, price = 0;
        switch (coffee) {
            case ESPRESSO:
                waterUsed = 250;
                beansUsed = 16;
                price = 4;
                break;
            case LATTE:
                waterUsed = 350;
                milkUsed = 75;
                beansUsed = 20;
                price = 7;
                break;
            case CAPPUCCINO:
                waterUsed = 200;
                milkUsed = 100;
                beansUsed = 12;
                price = 6;
                break;
            case BACK:
                return;
        }
        StringBuilder message = new StringBuilder("Sorry, not enough ");
        if(waterUsed>water){
            message.append("water");
        } else if(milkUsed>milk){
            message.append("milk");
        } else if(beansUsed>beans){
            message.append("coffee beans");
        } else if (cups ==0){
            message.append("disposable cups");
        } else {
            message.delete(0,message.length()).append("I have enough resources, making you a coffee!");
            water -= waterUsed;
            milk -= milkUsed;
            beans -= beansUsed;
            cups--;
            money += price;
        }
        System.out.println(message.toString());
        action = Action.MENU;

    }

    private void fillIngredient(int amount){
        switch(ingredient) {
            case WATER:
                water += amount;
                ingredient = Ingredient.MILK;
                break;
            case MILK:
                milk += amount;
                ingredient = Ingredient.BEANS;
                break;
            case BEANS:
                beans += amount;
                ingredient = Ingredient.CUPS;
                break;
            case CUPS:
                cups += amount;
                ingredient = Ingredient.WATER;
                action = Action.MENU;
                break;
        }
    }

    private void takeMoney(){
        System.out.println("I gave you $" + money);
        money = 0;
        action = Action.MENU;
    }
}
