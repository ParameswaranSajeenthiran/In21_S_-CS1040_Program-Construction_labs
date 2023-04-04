package farmativeAssesment2;

import java.util.ArrayList;
import java.util.List;

public class MusicalPerformanceTest {

    public static void main(String[] args) {
        List <MusicTrack> musicTracks=new ArrayList<>();
        musicTracks.add(new MusicTrack("Lavener Haze",10));
        musicTracks.add(new MusicTrack("All Too Well ",5));
        musicTracks.add(new MusicTrack("the lakes",7));
        musicTracks.add(new MusicTrack("The Man",7));
        musicTracks.add(new MusicTrack("Love story ",10));

        List <BackUpDancer> backUpDancers=new ArrayList<>();
        backUpDancers.add(new BackUpDancer("Stephanie"));
        backUpDancers.add(new BackUpDancer("Melenie"));


        List <BackUpSinger> backUpSingers =new ArrayList<>();
        backUpSingers.add(new BackUpSinger("jeslyn"));
        backUpSingers.add(new BackUpSinger("Jake"));


        LiveMusicalPerformance liveMusicalPerformance=new LiveMusicalPerformance("Glendale","2023","Eras Tour",

                new MainArtist("Taylor Swift"),musicTracks,backUpDancers,backUpSingers );

        liveMusicalPerformance.startPerformance();
    }






}


abstract class MusicalPerformance {
    private String venue;
    private String year;

    private String name;

    private MainArtist mainArtist;

    private List<MusicTrack> trackList;

    private List <BackUpDancer> backUpDancers;

    private List<BackUpSinger>backUpSingers;

    public MusicalPerformance(String venue, String year, String name, MainArtist mainArtist, List<MusicTrack> trackList, List<BackUpDancer> backUpDancers, List<BackUpSinger> backUpSingers) {
        this.venue = venue;
        this.year = year;
        this.name = name;
        this.mainArtist = mainArtist;
        this.trackList = trackList;
        this.backUpDancers = backUpDancers;
        this.backUpSingers = backUpSingers;
    }

    public String getVenue() {
        return venue;
    }



    public String getYear() {
        return year;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MainArtist getMainArtist() {
        return mainArtist;
    }

    public void setMainArtist(MainArtist mainArtist) {
        this.mainArtist = mainArtist;
    }

    public List<MusicTrack> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<MusicTrack> trackList) {
        this.trackList = trackList;
    }

    public List<BackUpDancer> getBackUpDancers() {
        return backUpDancers;
    }

    public void setBackUpDancers(List<BackUpDancer> backUpDancers) {
        this.backUpDancers = backUpDancers;
    }

    public List<BackUpSinger> getBackUpSingers() {
        return backUpSingers;
    }

    public void setBackUpSingers(List<BackUpSinger> backUpSingers) {
        this.backUpSingers = backUpSingers;
    }

    abstract void record();

    public void startPerformance(){
        System.out.println("Welcome to the performance "+this.name+" by "+ this.mainArtist.getName()+ "!");

        this.mainArtist.sing();
        this.backUpDancers.forEach(BackUpDancer::backup);
        this.backUpSingers.forEach(BackUpSinger::backup);
    }
}

class LiveMusicalPerformance  extends MusicalPerformance{

    public LiveMusicalPerformance(String venue, String year, String name, MainArtist mainArtist, List<MusicTrack> trackList, List<BackUpDancer> backUpDancers, List<BackUpSinger> backUpSingers) {
        super(venue, year, name, mainArtist, trackList, backUpDancers, backUpSingers);
    }


    void record() {
        System.out.println("this is the method record in the class " + getClass().getName());

    }

    public void interactWitAudience(){
        System.out.println("this is the method interactWithAudience in the class  "+getClass().getName());
    }
}

class  StudioMusicalPerformance extends  MusicalPerformance{

    public StudioMusicalPerformance(String venue, String year, String name, MainArtist mainArtist, List<MusicTrack> trackList, List<BackUpDancer> backUpDancers, List<BackUpSinger> backUpSingers) {
        super(venue, year, name, mainArtist, trackList, backUpDancers, backUpSingers);
    }

    public void audioProcessing (){
        System.out.println("this is the method audioProcessing in the class  "+getClass().getName());
    }

    void record() {
        System.out.println("this is the method record in the class " + getClass().getName());

    }
}

class MusicTrack{
    private String name;
    private int duration;

    public MusicTrack(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}

class Artist {
    private String name;

    public Artist(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}



interface Singer {
    public void sing();

}
interface Dancer {
    public  void dance();
}


class BackUpDancer  extends Artist implements IBackUp,Dancer {

    public BackUpDancer( String name ) {
        super(name);
    }

    public void backup() {

        System.out.println("This is the method dance of the clas BackUpDancer");
        dance();

    }

    @Override
    public void dance() {
        System.out.println("this is the method dance of the class " +getClass().getName());
    }
}
class BackUpSinger extends Artist implements  IBackUp ,Singer{


    public BackUpSinger(String name) {
        super(name);
    }

    public void backup() {
        System.out.println("this is the method backup of the class"+ getClass().getName());
        sing();

    }

    public void sing() {
        System.out.println("This is the method sing of the class BackUpSinger");


    }
}


interface  IBackUp {
    public  void backup();
}


class MainArtist extends Artist implements  Singer{
    public MainArtist(String name) {
        super(name);
    }

    public void sing() {
        System.out.println("This is the method sing in the class MainArtist"  );

    }
}