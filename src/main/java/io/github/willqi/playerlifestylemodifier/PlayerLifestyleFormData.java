package io.github.willqi.playerlifestylemodifier;

public class PlayerLifestyleFormData {

        private int id;

        private String playerName;

        public PlayerLifestyleFormData (int id, String playerName) {
            this.id = id;
            this.playerName = playerName;
        }

        public int getID () {
            return this.id;
        }

        public String getPlayerName () {
            return this.playerName;
        }


}
