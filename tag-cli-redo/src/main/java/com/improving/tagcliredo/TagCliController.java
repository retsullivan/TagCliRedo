package com.improving.tagcliredo;

import com.improving.tagcliredo.Models.Emote;
import com.improving.tagcliredo.Models.Item;
import com.improving.tagcliredo.Models.Weapon;
import com.improving.tagcliredo.database.DatabaseClient;
import com.improving.tagcliredo.database.EmoteDAO;
import com.improving.tagcliredo.database.ItemDAO;
import com.improving.tagcliredo.database.WeaponDAO;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class TagCliController {
    private final DatabaseClient databaseClient;
    private final EmoteDAO emoteDAO;
    private final WeaponDAO weaponDAO;
    private final ItemDAO itemDAO;
    private Scanner scanner = new Scanner(System.in);
    private String doThis;
    private String tableType;
    private String emoteName;
    private String emoteMessage;
    private String weaponName;
    private String weaponArea;
    private String itemName;
    int itemWeight;
    int itemValue;
    String itemBurnable;
    private String itemMagicQuality;


    public TagCliController(DatabaseClient databaseClient, EmoteDAO emoteDAO, WeaponDAO weaponDAO, ItemDAO itemDAO) {
        this.databaseClient = databaseClient;
        this.emoteDAO = emoteDAO;
        this.weaponDAO = weaponDAO;
        this.itemDAO = itemDAO;
    }

    public void inputProcesser() {

        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("Add to a table: Press 1");
            System.out.println("Read from a table: Press 2");
            System.out.println("> ");
            doThis = scanner.nextLine();

            if (doThis.equalsIgnoreCase("1")) { //write to a SQL table
                System.out.println("What table do you want to modify?");
                System.out.println("Emote: Press 1");
                System.out.println("Weapon: Press 2");
                System.out.println("Item: Press 3");
                tableType = scanner.nextLine();

                if (tableType.equalsIgnoreCase("1")) {
                    System.out.println("What is the name of the emote?");
                    emoteName = scanner.nextLine();
                    System.out.println("What is the emote message?");
                    emoteMessage = scanner.nextLine();
                    Emote emote = new Emote(emoteName, emoteMessage);
                    emoteDAO.insertEmote(emote);
                    System.out.println(emote.getName() + ":" + emote.getMessage()
                            + "added to the table");

                } else if (tableType.equalsIgnoreCase("2")) {
                    System.out.println("What is the name of the weapon?");
                    weaponName = scanner.nextLine();
                    System.out.println("What is the area");
                    weaponArea = scanner.nextLine();
                    Weapon weapon = new Weapon(weaponName, weaponArea, "weapon");
                    weaponDAO.insertWeapon(weapon);
                    System.out.println("Weapon: " + weapon.getName() + ": " + weapon.getArea()
                            + "added to the table");

                } else if (tableType.equalsIgnoreCase("3")) {
                    System.out.println("What is the name of the item? ");
                    itemName = scanner.nextLine();
                    System.out.println("What is the weight of the item? ");
                    itemWeight = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("What is the value of the item? ");
                    itemValue = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Is the item burnable? true or false ");
                    itemBurnable = scanner.nextLine();
                    scanner.nextLine();

                    System.out.println("Describe the item's magic qualities: ");
                    itemMagicQuality = scanner.nextLine();

                    Item item = new Item(itemName, itemWeight, itemValue, Boolean.parseBoolean(itemBurnable), itemMagicQuality);
                    itemDAO.insertItem(item);
                    System.out.println("New weapon, " + item.getName() + " added to the table");

                } else {
                    System.out.println("Invalid table name");
                }

            } else if (doThis.equalsIgnoreCase("2")) { //read from a SQL table

                System.out.println("What table do you want to read from?");
                System.out.println("Emote: Press 1");
                System.out.println("Weapon: Press 2");
                System.out.println("Item: Press 3");
                tableType = scanner.nextLine();

                if (tableType.equalsIgnoreCase("1")) {
                    System.out.println("What is the name of the emote?");
                    emoteName = scanner.nextLine();
                    Emote emote = emoteDAO.selectEmote(emoteName);

                } else if (tableType.equalsIgnoreCase("2")) {
                    System.out.println("What is the name of the weapon?");
                    weaponName = scanner.nextLine();
                    Weapon weapon = weaponDAO.selectWeapon(weaponName);
//
                } else if (tableType.equalsIgnoreCase("3")) {
                    System.out.println("What is the name of the item?");
                    itemName = scanner.nextLine();
                    Item item = itemDAO.selectItem(itemName);
//
                }

                else {
                    System.out.println("Invalid table name");
                }


            } else {
                System.out.println("Invalid command");
            }
        }
    }
}
