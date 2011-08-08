import java.awt.*;
import java.io.IOException;
import javax.media.*;
import javax.media.control.*;
import javax.media.format.*;
import javax.media.protocol.*;
import javax.media.util.BufferToImage;

public class VidcofedManager {

    public static void initializeCaptureProcess() {

	// Initailize capture device
	cdi = CaptureDeviceManager
		.getDevice("vfw:Microsoft WDM Image Capture (Win32):0");
	try {
	    Format[] cfmts = cdi.getFormats();
	    YUVFormat fmt = null;
	    for (int i = 0; i < cfmts.length; i++) {
		if (cfmts[i] instanceof YUVFormat) {
		    fmt = (YUVFormat) cfmts[i];
		}
	    }
	    cameraSrc = Manager.createDataSource(cdi.getLocator());
	    FormatControl[] fmtc = ((CaptureDevice) cameraSrc)
		    .getFormatControls();
	    for (int i = 0; i < fmtc.length; i++) {
		if (fmtc[i].setFormat(fmt) != null)
		    break;
	    }
	    process = Manager.createProcessor(cameraSrc);
	    process.realize();
	    while (process.getState() != Processor.Realized);
	    process.start();
	    pbSrc = (PushBufferDataSource) process.getDataOutput();
	    pbSrc.start();
	    strms = pbSrc.getStreams();
	    initialized = true;
	} catch (NoProcessorException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (NoDataSourceException e) {
	    e.printStackTrace();
	}
    }

    public static PushBufferStream getVideoStream() {

	return initialized ? strms[0] : null;
    }

    public static void stopCaptureProcess() {

	try {
	    pbSrc.stop();
	    process.close();
	    
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public static boolean getState() {
	return initialized;
    }

    private static CaptureDeviceInfo cdi;
    private static DataSource cameraSrc;
    private JCanvas pict;
    private static Processor process;
    private static PushBufferDataSource pbSrc;
    private static PushBufferStream[] strms;
    private static boolean initialized = false;
}