package formativeAssement1;
import java.util.*;
class Person{
    String name ;
    int id;
    public void checkScore(ScoreBoard scoreBoard ,int swimmerId){
        System.out.println(this.name +" checked score of swimmer "+swimmerId);
        System.out.println( "score= " +scoreBoard.scores.get(swimmerId));
    }
}
class Judge extends Person{
    public Judge( String name,int id) {
        this.name=name;
        this.id=id;
    }
    void blowWhistle(){
        System.out.println("----------------blowing whistle----------------");
    }
}
class Spectator extends Person{
    public Spectator( String name,int id) {
        this.name=name;
        this.id=id;
    }
    public void cheer(){
        System.out.println("spector "+ this.name+" cheering ");
    }
}
class Swimmer extends Person{
    public TouchPad touchPad;
    public Swimmer( String name,int id) {
        this.name=name;
        this.id=id;
        this.touchPad=new TouchPad();
    }
    void startSwimming(){
        Date startTime =new Date();
        System.out.println("swimmer "+ this.name + " started swimming " );
        this.touchPad.stampStartTime();
    }
    void touchTheTouchPad(TouchPad touchPad,ScoreBoard scoreBoard){
        this.touchPad.stampStoptime();
        touchPad.setHasTouched(true);
        System.out.println("swimmer "+ this.name+ " touched the touch pad");
    }
    void stopSwimming(){
        System.out.println( "swimmer "+this.name + " stopped swimming ");
    }
}
class SupportingStaff extends Person{
    public SupportingStaff(String name,int id){
        this.name=name;
        this.id=id;
    }
}
class TouchPad{
    boolean hasTouched;
    int swimmerId;
    Date startTime;
    Date stopTime ;
    void setHasTouched(boolean hasTouched){
        this.hasTouched=hasTouched;
    }
    void stampStartTime(){
        this.startTime=new Date();
    }
    void stampStoptime(){
        this.stopTime=new Date();
    }
}
class ScoreBoard{
    Map<Integer,Integer>scores=new HashMap<>();
    void calculateScores(List<Swimmer> swimmers){
        System.out.println("################# scores #####################");
        System.out.println("swimmer | time Taken | score");
        for ( Swimmer swimmer : swimmers){
            int
                    timeTaken=(swimmer.touchPad.stopTime.getSeconds()-swimmer.touchPad.startTime.getSeconds());
            int score = (100-timeTaken);
            scores.put(swimmer.id,score);
            System.out.println( swimmer.name+" "+ timeTaken +" secs " +score );
        }
        System.out.println("##############################################");
    }
}
public class SwimmingCompetition {
    List<Swimmer> swimmers =new ArrayList<>();
    List<Judge> judges =new ArrayList<>();
    List<Spectator> spectators =new ArrayList<>();
    List<SupportingStaff> supportingStaffs=new ArrayList<>();
    ScoreBoard scoreBoard =new ScoreBoard();
    TouchPad touchPad =new TouchPad();
    private static int personCouter=0;
    public void initializeObjects(){
        List<Person> swimmers =new ArrayList<>();
        List<Person> judges =new ArrayList<>();
        List<Person> spectators =new ArrayList<>();
        int noOfSwimmers=1;
        int noOfJudges=1;
        int noOfSpectators=1;
        int noOfSupportingStaff=1;
        Scanner scanner =new Scanner(System.in);
        System.out.println("enter the no of Swimmers ");
        noOfSwimmers=scanner.nextInt();
        createPersons(noOfSwimmers,"swimmer");
        System.out.println("Enter the no of Spectators ");
        noOfSpectators=scanner.nextInt();
        createPersons(noOfSpectators,"spectator");
        System.out.println("Enter the no of Judges ");
        noOfJudges= scanner.nextInt();
        createPersons(noOfJudges,"judge");
        System.out.println("Enter the no of Supporting Staff ");
        noOfSupportingStaff= scanner.nextInt();
        createPersons(noOfJudges,"supportingStaff");
    }
    public void createPersons(int count ,String type){
        int id;
        if(type=="swimmer"){
            List<Swimmer> personList=new ArrayList<>();
            for (int i=0; i<count;i ++) {
                id = personCouter++;
                personList.add(new Swimmer("swimmer" + id, id));
            }
            this.swimmers=personList;
        }
        else if (type=="judge"){
            List<Judge> personList=new ArrayList<>();
            for (int i=0; i<count;i ++) {
                id = personCouter++;
                personList.add(new Judge("judge" + id, id));
                this.judges=personList;
            }
        }else if (type=="supportingStaff"){
            List<SupportingStaff> personList=new ArrayList<>();
            for (int i=0; i<count;i ++) {
                id = personCouter++;
                personList.add(new SupportingStaff("supportingStaff" + id, id));
                this.supportingStaffs=personList;
            }
        }
        else{
            List<Spectator> personList=new ArrayList<>();
            for (int i=0; i<count;i ++) {
                id = personCouter++;
                personList.add(new Spectator("spector" + id, id));
            }
            this.spectators=personList;
        }
    }
    public void startSwimmingComp(){
        this.spectators.forEach(spectator->{
            spectator.cheer();
        });
        System.out.println("\n");
        this.judges.get(0).blowWhistle();
        System.out.println("\n");
        this.swimmers.forEach(swimmer->{
            swimmer.startSwimming();
        });
        System.out.println("\n");
        this.swimmers.forEach(swimmer -> {
            for (int i=0; i<5;i++)
                System.out.print(".");
            System.out.println("\n");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            swimmer.touchTheTouchPad(touchPad,scoreBoard);
        });
        System.out.println("\n");
        scoreBoard.calculateScores(this.swimmers);
        System.out.println("\n");
        this.spectators.get(0).checkScore(scoreBoard,0);
    }
    public void printObjects(){
        System.out.println("swimmers :"+ this.swimmers.size()+ " judges :" +this.judges.size()+
                " spectors :"+this.spectators.size() +" supporting staff : "+ this.supportingStaffs.size());
    }
    public static void main(String[] args) throws InterruptedException {
        SwimmingCompetition swimmingCompetition=new SwimmingCompetition();
        swimmingCompetition.initializeObjects();
        swimmingCompetition.printObjects();
        System.out.println("\n");
        swimmingCompetition.startSwimmingComp();
    }
}