import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import java.io.FileWriter;
import java.io.IOException;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class TwitterStreamer {

    public static void main(String[] args) {
        final Configuration configuration = new ConfigurationBuilder()
                .setDebugEnabled(true)
                .setOAuthConsumerKey("ilwoVufsfMw1ImKcOCtjy2oju") // paste your consumer key
                .setOAuthConsumerSecret("IG2P6uR60UsCqfAzB0Qfkr8BSdsaE263149UXmGs0MFdsnKzMn") //paste your CONSUMER_SECRET
                .setOAuthAccessToken("842395303051427840-YlMNFa4NrJ0ZgMG6x2UIUjAwqMC3fAs") //paste your OAUTH_ACCESS_TOKEN
                .setOAuthAccessTokenSecret("uu2HZ2jg5STlyI8U532flmuJVAVAY8KFUEzggRQ9LEWlR") // paste your OAUTH_ACCESS_TOKEN
                .build();

        StatusListener listener = new StatusListener() {

            public void onStatus(Status status) {

                System.out.println(status.getText());
                try
                {
                    String filename= "test.csv";
                    boolean append = true;
                    FileWriter fw = new FileWriter(filename,append);
                    fw.append(String.valueOf(status.getId()));//appends the string to the file
                    fw.append(",");
                    fw.append(String.valueOf(status.getCreatedAt()));//appends the string to the file
                    fw.append(",");
                    fw.append(String.valueOf(status.getGeoLocation()));//appends the string to the file
                    fw.append(",");
                    fw.append(String.valueOf(status.getLang() ));//appends the string to the file
                    fw.append(",");
                    fw.append(String.valueOf(status.getPlace() ));//appends the string to the file
                    fw.append(",");
                    fw.append(status.getText());
                    fw.append("\n");
                    fw.close();
                }
                catch(IOException ioe)
                {
                    System.err.println("IOException: " + ioe.getMessage());
                }
            }

            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
            }

            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            }

            public void onScrubGeo(long l, long l1) {

            }

            public void onStallWarning(StallWarning stallWarning) {

            }

            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };

        TwitterStream twitterStream = new TwitterStreamFactory(configuration).getInstance();
        twitterStream.addListener(listener);
        twitterStream.filter(new FilterQuery().track("facebook", "instagram", "twitter"));


    }
}
