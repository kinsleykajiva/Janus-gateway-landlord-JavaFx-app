package africa.jopen.models.forms.janusconfig.janus;


import com.fasterxml.jackson.annotation.JsonProperty;
 

 

public class Events{
    @JsonProperty("broadcast")
    public Broadcast getBroadcast() {
        return this.broadcast; }
    public void setBroadcast(Broadcast broadcast) {
        this.broadcast = broadcast; }
    Broadcast broadcast;
    @JsonProperty("disable")
    public Disable getDisable() {
        return this.disable; }
    public void setDisable(Disable disable) {
        this.disable = disable; }
    Disable disable;
    @JsonProperty("stats_period")
    public StatsPeriod getStats_period() {
        return this.stats_period; }
    public void setStats_period(StatsPeriod stats_period) {
        this.stats_period = stats_period; }
    StatsPeriod stats_period;
}
