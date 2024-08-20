public class ASCIIMagic {
    private static final String ASCIILegend = ".;coPBO?@■";

    public void printASCIIMap(char[][]asciiMap){
        for (int i = 0; i < asciiMap[0].length; i++) {
            for (int j = 0; j < asciiMap.length; j++) {
                System.out.print(asciiMap[j][i]);
            }
            System.out.println();
        }
    }

    public char[][]getASCIIMap(float[][]luminanceMap){
        int asciiIndex;
        int height = luminanceMap.length;
        int width = luminanceMap[0].length;
        char[][]asciiMap = new char[height+1][width+1];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                asciiIndex = (int)luminanceMap[i][j];
                asciiMap[i][j]= ASCIILegend.charAt(Math.min(asciiIndex,9));
            }
        }
        return asciiMap;
    }

}
