package africa.jopen.landlord.configs;

public enum Navigation {
    HOME("/views/home/home.fxml"),
    MAIN("/views/main.fxml"),
    SESSIONS("/views/janus/sessions.fxml"),
    JANUS_CONFIG("/views/api/janus-api.fxml"),
    LOGIN("/views/auth/login.fxml"),
    SETTINGS("/views/settings/settings.fxml");

    private final String path;

    Navigation(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
