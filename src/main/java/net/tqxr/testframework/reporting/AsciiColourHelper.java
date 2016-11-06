package net.tqxr.testframework.reporting;

class AsciiColourHelper {
    final static String RESET = (char) 27 + "[0m";


    static class AsciiCode {

        AsciiCode(int foreground) {
            this.foreground = foreground;
        }

        private int foreground;
        private int background = -1;

        private int bold = -1;
        private int underline = -1;

        public AsciiCode on(int colourCode) {
            this.background = colourCode;
            return this;
        }

        public AsciiCode dim() {
            this.bold = 21;
            return this;
        }

        AsciiCode bright() {
            this.bold = 1;
            return this;
        }

        AsciiCode underline() {
            this.underline = 4;
            return this;
        }

        void reset() {
            background = -1;
            bold = -1;
            underline = -1;
        }

        @Override
        public String toString() {

            StringBuilder outputString = new StringBuilder();
            outputString.append((char)27);
            outputString.append("[3").append(foreground);

            if (background != -1) {
                outputString.append(";4").append(background);
            }

            if (bold != -1) {
                outputString.append(";").append(bold);
            }

            if (underline != -1) {
                outputString.append(";").append(underline);
            }

            outputString.append("m");
            return outputString.toString();
        }
    }

    final static AsciiCode BLACK = new AsciiCode(0);
    final static AsciiCode RED = new AsciiCode(1);
    final static AsciiCode GREEN = new AsciiCode(2);
    final static AsciiCode YELLOW = new AsciiCode(3);
    final static AsciiCode BLUE = new AsciiCode(4);
    final static AsciiCode MAGENTA = new AsciiCode(5);
    final static AsciiCode CYAN = new AsciiCode(6);
    final static AsciiCode WHITE = new AsciiCode(7);


}
