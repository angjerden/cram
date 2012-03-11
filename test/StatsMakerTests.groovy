
/**
 * Created by IntelliJ IDEA.
 * User: b543840
 * Date: 2/19/12
 * Time: 4:32 PM
 * To change this template use File | Settings | File Templates.
 */
class StatsMakerTests extends GroovyTestCase {

    StatsMaker statsMaker

    public void setUp(){
        statsMaker = new StatsMaker("dummy", 5, 10)
    }

    public void tearDown(){

    }

    public void test_make_statistic_entry_without_date(){

        assertEquals "5/10\t[▊▊▊▊▊▊▊▊▊▊▊▊             ] 50% correct", statsMaker.makeStatisticEntryWODate()
    }
}
