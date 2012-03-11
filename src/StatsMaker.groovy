
/**
 * Created by IntelliJ IDEA.
 * User: b543840
 * Date: 2/19/12
 * Time: 3:35 PM
 * To change this template use File | Settings | File Templates.
 */
class StatsMaker {

    String rootFilename
    Integer numCorrect
    Integer total
    def final String block = "▊"
    def final Integer maximumBlocks = 25
    def final String statsDir = "dat"
    def final Integer numRecentStatsToRetrieve = 5

    public StatsMaker(String filename, Integer numCorrect, Integer total){
        rootFilename = filename.split(/\./)[0]
        this.numCorrect = numCorrect
        this.total = total
    }

    public writeStatisticsToFile(String statsEntry){
        File statsDirFile = new File(statsDir)
        if(!statsDirFile.exists()) statsDirFile.mkdir()
        File statsFile = new File("$statsDirFile/${rootFilename}.dat")
        statsFile.append(statsEntry)
    }

    public String makeStatisticEntry(){
        String now = new Date().format("dd.MM.yyyy HH:mm:ss")
        return "$now - ${makeStatisticEntryWODate()}\n"
    }

    public String makeStatisticEntryWODate(){ //method without date for test purposes
        def percentCorrect = (int)(numCorrect / total) * 100

        return "${numCorrect}/${total}\t${makePercentageBar(percentCorrect)} $percentCorrect% correct"
    }

    public String makePercentageBar(Integer percent){
        def blocksToWrite = (int)(maximumBlocks * (percent/100))
        def blankSpace = maximumBlocks - blocksToWrite
        def percentBarString = ""
        percentBarString += "["
        for(int i = 0; i < blocksToWrite; i++){
            percentBarString += block
        }
        for(int i = 0; i < blankSpace; i++){
            percentBarString += " "
        }

        percentBarString += "]"

        return percentBarString
    }

    public String retrieveRecentStats(){
        String returnString = ""
        File f = new File("dat/${rootFilename}.dat")
        if(f.exists()){
            def lines = []
            f.eachLine{ String line ->
                lines << line
            }
            def end = lines.size()
            def start = end - numRecentStatsToRetrieve
            if(start < 0) start = 0 //before we have built up at least 5 lines in the stats-file
            for(int i = start; i < end; i++){
                returnString += lines[i] + "\n"
            }
        }

        return returnString
    }
}
