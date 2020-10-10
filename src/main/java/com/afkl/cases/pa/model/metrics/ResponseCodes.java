package com.afkl.cases.pa.model.metrics;


public class ResponseCodes{
		private Long responseCode;
		private Long totalCount=0L;
		public ResponseCodes(){
			
		}
		public ResponseCodes(Long responseCode, Long totalCount) {
			this.responseCode = responseCode;
			this.totalCount = totalCount;
		}
		public Long getResponseCode() {
			return responseCode;
		}
		public void setResponseCode(Long responseCode) {
			this.responseCode = responseCode;
		}
		public Long getTotalCount() {
			return totalCount;
		}
		public void setTotalCount(Long totalCount) {
			this.totalCount = totalCount;
		}
}
		