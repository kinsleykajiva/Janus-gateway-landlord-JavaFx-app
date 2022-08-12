package africa.jopen.models.admin.handles;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Direction{
	@JsonProperty("audio-send")
	public boolean getAudioSend() {
		return this.audioSend; }
	public void setAudioSend(boolean audioSend) {
		this.audioSend = audioSend; }
	boolean audioSend;
	@JsonProperty("audio-recv")
	public boolean getAudioRecv() {
		return this.audioRecv; }
	public void setAudioRecv(boolean audioRecv) {
		this.audioRecv = audioRecv; }
	boolean audioRecv;
	@JsonProperty("video-send")
	public boolean getVideoSend() {
		return this.videoSend; }
	public void setVideoSend(boolean videoSend) {
		this.videoSend = videoSend; }
	boolean videoSend;
	@JsonProperty("video-recv")
	public boolean getVideoRecv() {
		return this.videoRecv; }
	public void setVideoRecv(boolean videoRecv) {
		this.videoRecv = videoRecv; }
	boolean videoRecv;
}
