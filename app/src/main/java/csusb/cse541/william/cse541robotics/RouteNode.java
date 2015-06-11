package csusb.cse541.william.cse541robotics;

class RouteNode {
        private int speed;
        private String  direction;
        private int time;
        public RouteNode (String direction, int speed, int time) {
            this.direction = direction;
            this.speed = speed;
            this.time = time;
        }
        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }