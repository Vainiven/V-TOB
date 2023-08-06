# V-TOB Bot for Xeros :crossed_swords:
![version](https://img.shields.io/badge/version-0.1-blue.svg)
![Author](https://img.shields.io/badge/author-Vainiven%20%26%20FVZ-orange.svg)
![discord](https://img.shields.io/badge/Discord-Vainiven%236986-blue.svg)

V-TOB Bot is a script developed for the Xeros private server. It is designed to automate Theatre of Blood (TOB) for improving gameplay experience and efficiency. 

## Features :sparkles:

- Advanced combat system with various loadouts (melee, ranged, magic).
- Efficient healing and potion usage logic.
- Precise in-game area detection and movement to bosses.
- Adaptability to different bosses with unique fight strategies.

## Prerequisites :clipboard:

- RuneScape client with Xeros server setup.
- Java development environment for running the script.

## Usage :rocket:

1. Clone the repository.
2. Open the project in your favourite Java IDE.
3. Run the script within the client.

## Code Snippets :computer:

Here are some key features demonstrated in the code snippets:

- **Loadout Configuration:**

    ```java
    final EquipmentLoadout loadouts = new EquipmentLoadout(Gears.MELEE, Gears.RANGED, Gears.MAGIC);
    ```

- **In-Game Area Detection and Boss Handling:**

    ```java
    public TobBoss getCurrentRoom() {
        ...
    }
    ```

- **Efficient Healing and Potion Usage:**

    ```java
    inventory.usePotions();
    inventory.eat();
    ```

- **Adaptive Combat Strategy:**

    ```java
    public void onProcess() {
        ...
    }
    ```

Please note, it's important to adapt the script based on the actual in-game conditions. Be considerate of other players, and make sure to follow the game's rules.

## Contact :email:

For any inquiries or issues, please contact me on Discord at `Vainiven#6986`.

## Disclaimer :warning:

This bot is intended for educational purposes only. Using it may violate the rules of the game, and I am not responsible for any possible consequences.
