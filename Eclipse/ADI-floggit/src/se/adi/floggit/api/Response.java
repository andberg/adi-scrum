package se.adi.floggit.api;

public final class Response<T>
{
	private ResponseType response;
	private T object;

	public Response(ResponseType response, T object)
	{
		this.response = response;
		this.object = object;
	}

	public ResponseType getResponse()
	{
		return response;
	}

	public T getObject()
	{
		return object;
	}
}
