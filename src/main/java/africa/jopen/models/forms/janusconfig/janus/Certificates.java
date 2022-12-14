package africa.jopen.models.forms.janusconfig.janus;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Certificates {
	CertPem              cert_pem;
	CertKey              cert_key;
	CertPwd              cert_pwd;
	DtlsAcceptSelfsigned dtls_accept_selfsigned;
	DtlsCiphers          dtls_ciphers;
	RsaPrivateKey        rsa_private_key;

	@JsonProperty ("cert_pem")
	public CertPem getCert_pem () {
		return this.cert_pem;
	}

	public void setCert_pem (CertPem cert_pem) {
		this.cert_pem = cert_pem;
	}

	@JsonProperty ("cert_key")
	public CertKey getCert_key () {
		return this.cert_key;
	}

	public void setCert_key (CertKey cert_key) {
		this.cert_key = cert_key;
	}

	@JsonProperty ("cert_pwd")
	public CertPwd getCert_pwd () {
		return this.cert_pwd;
	}

	public void setCert_pwd (CertPwd cert_pwd) {
		this.cert_pwd = cert_pwd;
	}

	@JsonProperty ("dtls_accept_selfsigned")
	public DtlsAcceptSelfsigned getDtls_accept_selfsigned () {
		return this.dtls_accept_selfsigned;
	}

	public void setDtls_accept_selfsigned (DtlsAcceptSelfsigned dtls_accept_selfsigned) {
		this.dtls_accept_selfsigned = dtls_accept_selfsigned;
	}

	@JsonProperty ("dtls_ciphers")
	public DtlsCiphers getDtls_ciphers () {
		return this.dtls_ciphers;
	}

	public void setDtls_ciphers (DtlsCiphers dtls_ciphers) {
		this.dtls_ciphers = dtls_ciphers;
	}

	@JsonProperty ("rsa_private_key")
	public RsaPrivateKey getRsa_private_key () {
		return this.rsa_private_key;
	}

	public void setRsa_private_key (RsaPrivateKey rsa_private_key) {
		this.rsa_private_key = rsa_private_key;
	}
}
