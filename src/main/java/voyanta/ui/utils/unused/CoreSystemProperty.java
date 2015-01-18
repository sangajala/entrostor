package voyanta.ui.utils.unused;


public enum CoreSystemProperty {

    SKIP_DB_TESTS("skip.db.tests"),
    SKIP_TM_TESTS("skip.tm.tests"),
    SKIP_LOG_TESTS("skip.log.tests"),
    LEGACY_LOG_READING("legacy.log.reading"),
    WITH_STREAMING_LOG("with.streaming.log"),
    KEEP_LOG_FILE("keep.logfile"),
    USE_REMOTE_BROWSER("remote"),
    USE_PROXY("use.proxy"),
    TEST_ENV("test.env");

    public String getPropertyName() {
        return propertyName;
    }

    private String propertyName;

    CoreSystemProperty(String propertyName) {

        this.propertyName = propertyName;
    }
}
