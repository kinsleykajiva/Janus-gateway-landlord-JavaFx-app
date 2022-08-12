package africa.jopen.models.admin.handles;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;


public class Sdps{
	@JsonProperty("profile")
	public String getProfile() {
		return this.profile; }
	public void setProfile(String profile) {
		this.profile = profile; }
	String profile;
	@JsonProperty("local")
	public String getLocal() {
		return this.local; }
	public void setLocal(String local) {
		this.local = local; }
	String local;
	@JsonProperty("remote")
	public String getRemote() {
		return this.remote; }
	public void setRemote(String remote) {
		this.remote = remote; }
	String remote;
}

