package se.adi.floggit.api;

public class Response
{
	private ResponseType response;
	private Object object;
	
	public Response(ResponseType response, Object object) {
		this.response = response;
		this.object = object;
	}

	public ResponseType getResponse()
	{
		return response;
	}

	public Object getObject()
	{
		return object;
	}
}
