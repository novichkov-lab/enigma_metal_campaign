package us.kbase.util;

import java.io.File;
import us.kbase.common.service.UObject;
import us.kbase.kbaseenigmametals.SamplePropertyMatrix;
import us.kbase.kbaseenigmametals.SamplePropertyMatrixUploader;

public class SamplePropertyDataImporter {

	private String fileName = null;
	private String token = null;
	private Long workspaceId = null;

	
	public SamplePropertyDataImporter (String fileName, Long workspaceId, String token) throws Exception{
		if (fileName == null) {
			System.out.println("cMonkey Expression data file name required");
		} else {
			this.fileName = fileName;
		}
		if (token == null) {
			throw new Exception("Token not assigned");
		} else {
			this.token = token;
		}
		
		if (workspaceId == null) {
			throw new Exception("Workspace name not assigned");
		} else {
			this.workspaceId = workspaceId;
		}

	}
	
	public void importMatrix (String matrixName) throws Exception {
		
		SamplePropertyMatrixUploader uploader = new SamplePropertyMatrixUploader();
		
		SamplePropertyMatrix matrix = new SamplePropertyMatrix();
		matrix.setName(matrixName);
		
		File inputFile = new File(this.fileName);
		
		matrix = uploader.generateSamplePropertyMatrix(inputFile, matrix);
		//System.out.println(matrix.toString());
		WsDeluxeUtil.saveObjectToWorkspaceRef(UObject.transformObjectToObject(matrix, UObject.class), "KBaseEnigmaMetals.WellSampleMatrix", workspaceId, matrixName, "https://ci.kbase.us/services/ws", token.toString());
	}
	

}
