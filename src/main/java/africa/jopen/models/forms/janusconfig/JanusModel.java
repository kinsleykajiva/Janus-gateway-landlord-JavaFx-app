package africa.jopen.models.forms.janusconfig;

import africa.jopen.janus.settings.SettingsReq;
import africa.jopen.models.forms.janusconfig.janus.JanusObject;
import africa.jopen.utils.XUtils;
import com.dlsc.formsfx.model.structure.Element;
import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Section;
import com.dlsc.formsfx.model.validators.StringLengthValidator;
import com.dlsc.formsfx.view.controls.SimpleRadioButtonControl;
import com.dlsc.formsfx.view.util.ColSpan;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class JanusModel {
	static        Logger      logger    = Logger.getLogger(JanusModel.class.getName());
	private       JanusObject janusConfigs;
	private       Form        formInstance;
	public static String      jsonJanus = """
			{
			    "data": {
			        "nat": {
			            "ice_enforce_list": {
			                "lineValue": "eth0",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "turn_rest_api_method": {
			                "lineValue": "GET",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "turn_type": {
			                "lineValue": "udp",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "allow_force_relay": {
			                "lineValue": "true",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "stun_port": {
			                "lineValue": "3478",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "ice_ignore_list": {
			                "lineValue": "vmnet",
			                "comment": "--",
			                "commented": false,
			                "required": false
			            },
			            "nice_debug": {
			                "lineValue": "false",
			                "comment": "--",
			                "commented": false,
			                "required": true
			            },
			            "turn_rest_api_key": {
			                "lineValue": "anyapikeyyoumayhaveset",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "ice_lite": {
			                "lineValue": "true",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "nat_1_1_mapping": {
			                "lineValue": "1.2.3.4",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "ice_keepalive_conncheck": {
			                "lineValue": "true",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "turn_rest_api": {
			                "lineValue": "http://yourbackend.com/path/to/api",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "stun_server": {
			                "lineValue": "stun.voip.eutelia.it",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "full_trickle": {
			                "lineValue": "true",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "turn_port": {
			                "lineValue": "3478",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "turn_user": {
			                "lineValue": "myuser",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "ice_tcp": {
			                "lineValue": "true",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "keep_private_host": {
			                "lineValue": "true",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "ignore_mdns": {
			                "lineValue": "true",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "ignore_unreachable_ice_server": {
			                "lineValue": "true",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "ice_nomination": {
			                "lineValue": "regular",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "turn_server": {
			                "lineValue": "myturnserver.com",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "turn_pwd": {
			                "lineValue": "mypassword",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            }
			        },
			        "general": {
			            "debug_timestamps": {
			                "lineValue": "true",
			                "comment": "Whether to show a timestamp for each log line",
			                "commented": false,
			                "required": true
			            },
			            "server_name": {
			                "lineValue": "MyJanusInstance",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "event_loops": {
			                "lineValue": "8",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "recordings_tmp_ext": {
			                "lineValue": "tmp",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "reclaim_session_timeout": {
			                "lineValue": "0",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "candidates_timeout": {
			                "lineValue": "45",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "events_folder": {
			                "lineValue": "/opt/janus/lib/janus/events",
			                "comment": "Event handlers folder",
			                "commented": false,
			                "required": true
			            },
			            "admin_secret": {
			                "lineValue": "janusoverlord",
			                "comment": "--",
			                "commented": false,
			                "required": true
			            },
			            "interface": {
			                "lineValue": "1.2.3.4",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "protected_folders": {
			                "lineValue": [
			                    "/bin",
			                    "/boot",
			                    "/dev",
			                    "/etc",
			                    "/initrd",
			                    "/lib",
			                    "/lib32",
			                    "/lib64",
			                    "/proc",
			                    "/sbin",
			                    "/sys",
			                    "/usr",
			                    "/var",
			                    "/opt/janus/bin",
			                    "/opt/janus/etc",
			                    "/opt/janus/include",
			                    "/opt/janus/lib",
			                    "/opt/janus/lib32",
			                    "/opt/janus/lib64",
			                    "/opt/janus/sbin"
			                ],
			                "comment": "--",
			                "commented": false,
			                "required": true
			            },
			            "loggers_folder": {
			                "lineValue": "/opt/janus/lib/janus/loggers",
			                "comment": "External loggers folder",
			                "commented": false,
			                "required": true
			            },
			            "plugins_folder": {
			                "lineValue": "/opt/janus/lib/janus/plugins",
			                "comment": "Plugins folder",
			                "commented": false,
			                "required": true
			            },
			            "debug_level": {
			                "lineValue": "4",
			                "comment": "Debug/logging level, valid values are 0-7",
			                "commented": false,
			                "required": true
			            },
			            "log_to_file": {
			                "lineValue": "/path/to/janus.log",
			                "comment": "Whether to use a log file or not",
			                "commented": true,
			                "required": false
			            },
			            "configs_folder": {
			                "lineValue": "/opt/janus/etc/janus",
			                "comment": "Configuration files folder",
			                "commented": false,
			                "required": true
			            },
			            "log_to_stdout": {
			                "lineValue": "false",
			                "comment": "Whether the Janus output should be written to stdout or not (default=true)",
			                "commented": true,
			                "required": false
			            },
			            "daemonize": {
			                "lineValue": "true",
			                "comment": "Whether Janus should run as a daemon  or not (default=run in foreground)",
			                "commented": true,
			                "required": false
			            },
			            "transports_folder": {
			                "lineValue": "/opt/janus/lib/janus/transports",
			                "comment": "Transports folder",
			                "commented": false,
			                "required": true
			            },
			            "token_auth_secret": {
			                "lineValue": "janus",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "log_prefix": {
			                "lineValue": "[janus]",
			                "comment": "In case you want log lines to be prefixed by some , custom text, you can use the 'log_prefix' property. It supports terminal colors, meaning something like \\"[\\\\x1b[32mjanus\\\\x1b[0m] \\" would show a green \\"janus\\" string in square brackets (assuming debug_colors=true).",
			                "commented": true,
			                "required": false
			            },
			            "token_auth": {
			                "lineValue": "true",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "no_webrtc_encryption": {
			                "lineValue": "true",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "pid_file": {
			                "lineValue": "/path/to/janus.pid",
			                "comment": "PID file to create when Janus has been started, and to destroy at shutdown",
			                "commented": true,
			                "required": false
			            },
			            "debug_locks": {
			                "lineValue": "true",
			                "comment": "Whether to enable debugging of locks (very verbose!)",
			                "commented": true,
			                "required": false
			            },
			            "hide_dependencies": {
			                "lineValue": "true",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "opaqueid_in_api": {
			                "lineValue": "8",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "api_secret": {
			                "lineValue": "janusrocks",
			                "comment": "String that all Janus requests must contain to be accepted/authorized by the Janus core. Useful if you're wrapping all Janus API requests in your servers (that is, not in the browser",
			                "commented": false,
			                "required": false
			            },
			            "session_timeout": {
			                "lineValue": "60",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            }
			        },
			        "transports": {
			            "disable": {
			                "lineValue": "libjanus_rabbitmq.so",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            }
			        },
			        "certificates": {
			            "cert_pwd": {
			                "lineValue": "secretpassphrase",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "cert_key": {
			                "lineValue": "/path/to/key.pem",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "rsa_private_key": {
			                "lineValue": "false",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "dtls_accept_selfsigned": {
			                "lineValue": "false",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "cert_pem": {
			                "lineValue": "/path/to/certificate.pem",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "dtls_ciphers": {
			                "lineValue": "your-desired-openssl-ciphers",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            }
			        },
			        "loggers": {
			            "disable": {
			                "lineValue": "libjanus_jsonlog.so",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            }
			        },
			        "plugins": {
			            "disable": {
			                "lineValue": "libjanus_voicemail.so,libjanus_recordplay.so",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            }
			        },
			        "media": {
			            "rtp_port_range": {
			                "lineValue": "20000-40000",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "dtls_mtu": {
			                "lineValue": "1200",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "nack_optimizations": {
			                "lineValue": "true",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "dscp": {
			                "lineValue": "46",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "ipv6": {
			                "lineValue": "true",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "ipv6_linklocal": {
			                "lineValue": "true",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "dtls_timeout": {
			                "lineValue": "500",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "no_media_timer": {
			                "lineValue": "1",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "slowlink_threshold": {
			                "lineValue": "4",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "min_nack_queue": {
			                "lineValue": "500",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "twcc_period": {
			                "lineValue": "100",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            }
			        },
			        "events": {
			            "broadcast": {
			                "lineValue": "true",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            },
			            "disable": {
			                "lineValue": "libjanus_sampleevh.so",
			                "comment": "--",
			                "commented": false,
			                "required": true
			            },
			            "stats_period": {
			                "lineValue": "5",
			                "comment": "--",
			                "commented": true,
			                "required": false
			            }
			        }
			    },
			    "success": true,
			    "message": "Get current settings"
			}
						
			""";

	/**
	 * Creates or simply returns to form singleton instance.
	 *
	 * @return Returns the form instance.
	 */
	public Form getFormInstance () {
		if (formInstance == null) {
			try {
				// loads the default data
				//jsonJanus = "";
				ObjectMapper om = new ObjectMapper();
				om.enable(SerializationFeature.WRAP_ROOT_VALUE);
				janusConfigs = om.readValue(jsonJanus, JanusObject.class);

			} catch (IOException e) {
				logger.severe(e.getMessage());
				e.printStackTrace();
			}
			createForm();
		}

		return formInstance;
	}
	public List<Section> sectionsList = new ArrayList<>();
	/**
	 * Creates a new form instance with the required information.
	 */
	private void createForm () {
		JSONObject    data         = new JSONObject(jsonJanus);
		JSONObject    obj          = data.getJSONObject("data");


		for (String sectionName : obj.keySet()) {
			var section = obj.getJSONObject(sectionName);
			sectionsList.add(builderFormSection(obj.getJSONObject(sectionName), sectionName));
		}
		Section[] myArray = new Section[sectionsList.size()];
		sectionsList.toArray(myArray);
		formInstance = Form.of(
				sectionsList.toArray(myArray)
//				builderFormSection(obj.getJSONObject("general"), "general")
		);

	}

	private Section builderFormSection (JSONObject section, String sectionName) {
		List<Element> elements = new ArrayList<Element>();
		for (String target : section.keySet()) {
			var objStage  = section.getJSONObject(target);
			var commented = objStage.getBoolean("commented");
			var comment = objStage.getString("comment");
			var required  = objStage.getBoolean("required");
			if (objStage.get("lineValue") instanceof String) {
				var lineValue = objStage.getString("lineValue");

				if (lineValue.equals("true") || lineValue.equals("false")) { // true or false options
					elements.add(
							Field.ofSingleSelectionType(Arrays.asList("true", "false"), lineValue.equals("true") ? 0 : 1)
									.label(target)
									.span(ColSpan.HALF)
									/*.validate(StringLengthValidator.exactly(2, "Must not be empty"))*/
									.editable(true)
									/*.tooltip(comment)*/
									.render(new SimpleRadioButtonControl<>())
					);
				} else {
					elements.add(
							Field.ofStringType(lineValue)
									.span(ColSpan.HALF)
									/*.tooltip(comment)*/
									.label(target)
					);
				}

			} else if (objStage.get("lineValue") instanceof JSONArray) {
				var           lineValue = objStage.getJSONArray("lineValue");
				StringBuilder builder   = new StringBuilder();
				for (int i = 0; i < lineValue.length(); i++) {
					String line = lineValue.getString(i);
					builder.append(line).append(",");
				}
				builder.deleteCharAt(builder.length() - 1);
				elements.add(
						Field.ofStringType(builder.toString())
								.span(ColSpan.HALF)
								/*.tooltip(comment)*/
								.label(target)
				);
			}
			elements.add(Field.ofBooleanType(false)
					.span(ColSpan.HALF)
					.editable(required)
							.tooltip(required ? " Cant Comment/Disable Field" : "Can Comment/Disable" )
					.label("Comment/Disable Out"));

		}

		Element[] myArray = new Element[elements.size()];
		elements.toArray(myArray);
		return Section
				.of(elements.toArray(myArray))
				.collapse(true).collapsible(true)
				.title(StringUtils.capitalize(sectionName));

	}

}
