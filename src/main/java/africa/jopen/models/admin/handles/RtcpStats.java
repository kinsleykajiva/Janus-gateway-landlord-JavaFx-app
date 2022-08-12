package africa.jopen.models.admin.handles;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;


public class RtcpStats{
	@JsonProperty("audio")
	public Audio getAudio() {
		return this.audio; }
	public void setAudio(Audio audio) {
		this.audio = audio; }
	Audio audio;
}
