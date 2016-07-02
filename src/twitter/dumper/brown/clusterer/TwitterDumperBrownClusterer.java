/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter.dumper.brown.clusterer;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author Asus
 */
public class TwitterDumperBrownClusterer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String consumer_key     = "SNLe1piD2RCHTd9JZJO9WNQNy";
        String consumer_secret  = "tIYB9BkPfYxjEOrideAv21TcOd08apo8YAnweZoKb2CvadTLxt";
        String token            = "3213949031-RWUhQyr39e4JXS5FLmbcE74XrmFnI1EN6MXTJXs";
        String token_secret     = "dDfW347PjYW6uHx263Ifg4t1ljylcz4YVfyPeBy9O4LW4";
        
        ConfigurationBuilder conf = new ConfigurationBuilder();
        conf.setOAuthConsumerKey(consumer_key).setOAuthConsumerSecret(consumer_secret)
                .setOAuthAccessToken(token).setOAuthAccessTokenSecret(token_secret);
        TwitterStream stream = new TwitterStreamFactory(conf.build()).getInstance();
        
        stream.addListener(new StatusListener() {

            @Override
            public void onStatus(Status status) {
                if(status.getLang().equals("id")){
                    System.out.println("["+status.getId()+"] : "+status.getText());
                }else{
                    //System.out.println("["+status.getLang()+"] : "+status.getText());
                    System.out.println(status.getText());
                }
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice sdn) {
            }

            @Override
            public void onTrackLimitationNotice(int i) {
                System.out.println("[WARNING] Track limitation raised");
            }

            @Override
            public void onScrubGeo(long l, long l1) {
                System.out.println("[WARNING] Scrub Geo Detected");
            }

            @Override
            public void onStallWarning(StallWarning sw) {
                System.out.println("[WARNING] Stall detected");
            }

            @Override
            public void onException(Exception excptn) {
                System.out.println("[ERROR] Exception DETECTED");
                excptn.printStackTrace();
            }
        });
        
        
        FilterQuery filter = new FilterQuery();
        filter.locations(new double[][]{
            new double[]{105.2098,-8.7806},
            new double[]{114.6056,-5.8758}
        });
        
        stream.filter(filter);
//        stream.sample();
    }
    
}
