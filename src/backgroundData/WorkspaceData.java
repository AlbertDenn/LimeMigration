package backgroundData;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class WorkspaceData {

	private Integer id = null;
	private Integer defaultMimId = null;
	private String description = null;
	private HashMap<String, String> parameters = new HashMap<String, String>();
	private ArrayList<String> shortcodes = new ArrayList<String>();

	public WorkspaceData() {

		parameters.put("show_home", "true");
		parameters.put("top_branding_image", "images/OGA980x100.png");
		parameters.put("see_what_is_new", "false");
		parameters.put("hide_optionscombo", "true");
		parameters.put("hide_reportscombo", "true");
		parameters.put("hide_creditslabel", "true");
		parameters.put("hide_instancescombo", "false");
		
		// This is a list of roles separated by ';' NOT a boolean property
		parameters.put("show_roles", ";"); 
		parameters.put("messangi_redirect_url", "http://api.messangi.com");
		parameters.put("access_public_key", "4E6A1AB0-2209-4626-96A7-57A07C355D5C");
		parameters.put("access_private_key", "6E9496F8-205E-4B68-8713-7992C0362A89");
		parameters.put("messangi_root_public_key", "D79BFFVx2TjXcJrrnktB");
		parameters.put("messangi_root_private_key", "wzuuzVeVA4hR9Xc8kWQB");
		parameters.put("messangi_client_debug", "false");
		parameters.put("messangi_client_url", "https://api.messangi.com/messangi-staging/rest/message");
		parameters.put("messangi_client_cert", "/opt/mimall/messangi/cert/");
		parameters.put("messangi_client_charset_signer", "UTF-8");
		parameters.put("optout_msg_bc", "Send STOP to stop");
		parameters.put("max_msg_preview", "10");
		parameters.put("max_msg_broadcast", "1000");
		parameters.put("welcome_action", "execute?MIMAction=doWelcome");
		parameters.put("cdrman_server", "backend6.ogangi.com");
		parameters.put("cdrman_port", "9001");
		parameters.put("cdrman_file", "/CDRManager3/external?action=getHourlyStats");
		parameters.put("blacklist_def_sc", "-2");
		parameters.put("blacklist_def_type", "1");
		parameters.put("timezone", "EST5EDT");
		parameters.put("messangi_app_name", "Messangi");
		parameters.put("default_carrier_name", "Syniverse");
		parameters.put("default_carrier_id", "5");
		parameters.put("social_reg_callback", "http://api.messangi.com");
        parameters.put("payment_paypal_response_callback", "http://");
		parameters.put("mimapp_hide_internal_lists", "true");
		parameters.put("mimapp_email_footer", "Required");
		parameters.put("mimapp_built_in_view", "false");
		parameters.put("mimapp_built_in_view_max_size", "15");
		parameters.put("mimapp_url_shortener", "bit.ly");
		parameters.put("mimapp_thousand_separator", ",");
		parameters.put("mimapp_default_unsubscribe", "true");
		parameters.put("mimapp_broadcast_email_from", "postmaster@ogangi.net");
		parameters.put("mimapp_broadcast_without_whitelist", "true");
		
		// parameters.put("encoding_mmc", "ISO-8859-1");
		parameters.put("encoding_mmc", "UTF-8");
		parameters.put("segmentation_kibana_url", "http://");
		parameters.put("broadcast_allow_multiple_message", "true");
		parameters.put("broadcast_message_max_length", "160");

		parameters.put("broadcast_restrict_message_length", "false");
		parameters.put("broadcast_timezone_parameter", " ");
		parameters.put("segmentation_global_filter", " ");
		parameters.put("custom_service_url", " ");
		parameters.put("bitly_accesstoken", "c9685e9523ea699edf972b4125243683f95decfa");
		parameters.put("google_apikey", " ");
		parameters.put("segmented_whitelist_filters", " ");
		parameters.put("elasticsearch_type_prefix", "Required");
		parameters.put("segmentation_main_list", " ");
		parameters.put("elasticsearch_export_scroll_size", "2000");
		parameters.put("mimapp_server_onair_quota", "30");
		parameters.put("mimapp_time_activity_onair", "-1");
		parameters.put("mimapp_broadcast_restricted_schedule_hours", " ");
		parameters.put("mimapp_broadcast_restricted_schedule_days", ";");
		parameters.put("mimapp_dr_registered_delivery", "1");
		parameters.put("mimapp_message_report_max_range", "93");
		parameters.put("regular_list_extended_attr_enable", "false");
		parameters.put("mimapp_minimun_geofence_radius", "200");
		parameters.put("mimapp_mma_compliance", "true");
		parameters.put("mimapp_mail_server", "mail.ogangi.com");
		parameters.put("mimapp_mail_sender_mail", "administrator@ogangi.com");
		parameters.put("mimapp_mail_send_confirmation_on_create", "true");
		parameters.put("mimapp_default_optin_status", "0");
		parameters.put("mimapp_update_main_list", "false");
		parameters.put("mimapp_email_notifications_list_checkers", " ");
		parameters.put("mimapp_broadcast_whitelist_id_default", " ");
		parameters.put("mimapp_broadcast_preview_whitelist", "false");
		parameters.put("mimapp_hide_update_main_list", "false");
		parameters.put("segmentation_hidden_filters", " ");
		parameters.put("segmentation_list_timestamp_name", " ");
		parameters.put("broadcast_report_start_day_default", "30");
		parameters.put("dashboard_kmeans_clusters", "3");
		parameters.put("segmentation_extra_filters", " ");
		parameters.put("internal_api_callbacks", "false");
		parameters.put("segmentation_replace_filter_values", "true");
		parameters.put("androidPkpass", "false");
		parameters.put("credits_threshold", "-1");
		parameters.put("credits_alert_email_frequency", "-1");
		parameters.put("credits_threshold", "-1");
		parameters.put("credits_alert_email_frequency", "-1");

		parameters.put("chaser_delays_in_minutes", "1,2,3");
		parameters.put("mimapp_default_broadcast_sc", "-");
		parameters.put("mimapp_loyalty_number_label", "LN");
		parameters.put("defaultEmailTemplate", "false");
		parameters.put("defaultEmailTemplateName", "defaultEmail.html");
		parameters.put("mimapp_distribution_list_alert_quote", "disable");
		parameters.put("mimapp_distribution_list_alert_timespan_min", "1440");
		parameters.put("mimapp_distribution_list_quote_trigger_alert_percent", "90");
		parameters.put("mimapp_distribution_list_quote_trigger_silent_percent", ";");
		parameters.put("mimapp_distribution_list_quote_email", ";");
		parameters.put("mimapp_multiple_choice_idle_time", "10");
		
	    parameters.put("verify_keyphrases", "false");
	    parameters.put("verify_campaign_name", "false");
	    parameters.put("mimapp_aotoregister_sales_company_name","none");
	    
	    parameters.put("hide_optout_msg", "true");
	    
	    parameters.put("broadcast_mt_limit_onair", "10000");

	    parameters.put("wallet_facebook_app", "1578878409074226");
	    parameters.put("wallet_map_zoom", "18");

	    //Broadcast Configuration
	    parameters.put("mimapp_ldap_charset", "default");
	    parameters.put("mimapp_broadcast_conf_throttle_messages", "100");
	    parameters.put("mimapp_broadcast_conf_throttle_unit", "1");
	    parameters.put("mimapp_broadcast_max_throttle", "90");
	    parameters.put("mimapp_broadcast_conf_throttle_hide_from_user", "false");
	    parameters.put("mimapp_advance_throttle", "true");

	    parameters.put("mimapp_validate_password", "false");

		parameters.put("statistics_kibana_url", "bi/#/dashboard/New-Events");
	    parameters.put("mimapp_list_broadcasts_by_user", "false");
	    parameters.put("mimapp_manage_broadcasts_by_user_logged", "false");
	    parameters.put("mimapp_broadcast_use_owncode", "false");
	    parameters.put("broadcast_resume_email_to", " ");
	    parameters.put("welcome_hot_buttons", "true");
	    parameters.put("welcome_hot_buttons_whitelist",",");

		parameters.put("excel_column_limit", "30");
		
		parameters.put("mimapp_internal_events_wallet", ";");
		
		parameters.put("discard_messages_per_mim",",");
		parameters.put("discard_messages_per_recipient_after","-1");
		parameters.put("discard_messages_per_recipient_type","ALL");

		parameters.put("validate_customer_optin_status_on_broadcasts","true");
		
		//Scratchcard configuration
		parameters.put("scratchcard_max_files_eswipe","19");
		
		parameters.put("mimapp_broadcast_sender_synchronizer_use_old", "true");
		parameters.put("mimapp_broadcast_sender_synchronizer_queriers", "5");
		parameters.put("mimapp_broadcast_sender_synchronizer_senders", "1");
		parameters.put("blacklist_all_sc", "false");
		parameters.put("advanced_options_template", "true");
		parameters.put("allow_date_field_void", "false");
		parameters.put("date_format", "MM/dd/yyyy hh:mm a");
		
		parameters.put("recharge_after_question_answered", "false");

	}

		
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDefaultMimId() {
		return defaultMimId;
	}

	public void setDefaultMimId(Integer defaultMimId) {
		this.defaultMimId = defaultMimId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public HashMap<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(HashMap<String, String> parameters) {
		this.parameters = parameters;
	}
	
	public void overrideParameter(String key, String value) {
		this.parameters.put(key, value);
	}

	public ArrayList<String> getShortcodes() {
		return shortcodes;
	}

	public void setShortcodes(ArrayList<String> shortcodes) {
		this.shortcodes = shortcodes;
	}

	
	public static void delete(Connection conn, Integer id)
			throws Exception {

		
		CallableStatement cs = null;

		try {
			
			cs = conn.prepareCall("{call delete_instance(?)}");
			cs.setInt(1, id.intValue());
			cs.executeUpdate();

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
			}
			throw e;
		} finally {
			try {
				cs.close();
			} catch (Exception e) {
			}
			try {
				conn.close();
			} catch (Exception e) {
			}
		}

	}
	
	public static String save(String login, Connection conn, WorkspaceData wsd, String ftextName, String defaultResponse) throws Exception {
		
		PreparedStatement ps = null;
		CallableStatement cs = null;
		int newId=-1;
		
		try {
			int index = 0;
			conn.setAutoCommit(false);
			if (wsd.getId().intValue() == -1) {
				
				String name = wsd.getShortcodes().get(0);
				String shortcode = null;
				if( wsd.getShortcodes().get(0).indexOf("(") != -1){
					shortcode = wsd.getShortcodes().get(0).substring(0, wsd.getShortcodes().get(0).indexOf("("));
				}
				else{
					shortcode = wsd.getShortcodes().get(0);
				}
				
				if (wsd.getDescription().isEmpty()) {
					name = wsd.getDescription();
				}
				
				cs = conn.prepareCall("{call add_instance(?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setString(1, name);
				cs.setString(2, shortcode);
				cs.setNull(3, Types.VARCHAR);
				cs.setNull(4, Types.VARCHAR);
				cs.setString(5, login);
				cs.setString(6, ftextName);
				cs.setString(7, defaultResponse);
				cs.setString(8, "Default");
				cs.setNull(9, Types.VARCHAR);
				cs.registerOutParameter(10, Types.INTEGER);
				cs.registerOutParameter(11, Types.INTEGER);
				cs.executeUpdate();
				
				newId= new Integer(cs.getInt(10));
				wsd.setId(new Integer(cs.getInt(10)));
				wsd.setDefaultMimId(new Integer(cs.getInt(11)));
				
				
				
				index = 1;
			} else {
				ps = conn.prepareStatement("delete from instancexsc where insid = ?");
				ps.setInt(1, wsd.getId().intValue());
				ps.execute();
				ps.close();
				
				ps = conn.prepareStatement("delete from instance_conf where instanceid = ?");
				ps.setInt(1, wsd.getId().intValue());
				ps.execute();
				ps.close();
				
				ps = conn.prepareStatement("update instance set description = ? where id = ?");
				ps.setString(1, wsd.getDescription());
				ps.setInt(2, wsd.getId().intValue());
				ps.execute();
				ps.close();
				
				ps = conn.prepareStatement("delete from keywordxworkspace where insid = ?");
				ps.setInt(1, wsd.getId().intValue());
				ps.execute();
				ps.close();
			}
			
			ps = conn.prepareStatement("insert into instancexsc (insid, shortcode, prize) values (?, ?, null)");
			
			ArrayList<String> sc = new ArrayList<String>();
			
			for(int i = 0; i < wsd.getShortcodes().size(); i++){
				if(wsd.getShortcodes().get(i).indexOf("(") != -1){
					sc.add(wsd.getShortcodes().get(i).substring(0, wsd.getShortcodes().get(i).indexOf("(")));
				}
				else{
					sc.add(wsd.getShortcodes().get(i));
				}
			}
			
			Set<String> set = new LinkedHashSet<String>(sc);
			
			for (int i = index; i < set.size(); i++) {
				ps.setInt(1, wsd.getId().intValue());
				ps.setString(2,  sc.get(i));
				ps.addBatch();
			}

			ps.executeBatch();
			ps.close();
			
			//Key words
			ps = conn.prepareStatement("insert into keywordxworkspace (shortcode, keyword, insid) values (?, ?, ?)");

			for (int i = 0; i < wsd.getShortcodes().size(); i++) {
				
				if(wsd.getShortcodes().get(i).indexOf("(") != -1){
					String shortcode =  wsd.getShortcodes().get(i).substring(0, wsd.getShortcodes().get(i).indexOf("("));
					ps.setString(1, shortcode);
					String keyWord =  wsd.getShortcodes().get(i).substring(wsd.getShortcodes().get(i).indexOf("(")+1, wsd.getShortcodes().get(i).length()-1);
					ps.setString(2, keyWord);
				}
				else{
					continue;
				}
				ps.setInt(3, wsd.getId().intValue());
				ps.addBatch();
			}
			
			ps.executeBatch();
			ps.close();
			
			ps = conn.prepareStatement("insert into instance_conf (instanceid, key, value, system_value) values (?, ?, ?, 0)");
			Iterator<String> keys = wsd.getParameters().keySet().iterator();

			while (keys.hasNext()) {
				String key = (String) keys.next();
				ps.setInt(1, wsd.getId().intValue());
				ps.setString(2, key);
				ps.setString(3, wsd.getParameters().get(key));
				ps.addBatch();
			}
			
			ps.executeBatch();
			ps.close();
			
			conn.commit();
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (Exception e2) {
				e.printStackTrace();
			}
			throw e;
		} finally {
			try {
				cs.close();
			} catch (Exception e) {
			}
			try {
				ps.close();
			} catch (Exception e) {
			}
			try {
				conn.setAutoCommit(true);
			} catch (Exception e) {
			}
//			try {
//				conn.close();
//			} catch (Exception e) {
//			}
		}
		
		return String.valueOf(newId);
		
	}

}