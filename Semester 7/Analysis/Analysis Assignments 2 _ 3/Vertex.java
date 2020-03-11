
public class Vertex {
	protected String _strUniqueID, _strData;
	protected int _nX, _nY;
	protected boolean visited;
	
	public Vertex(String _strUniqueID,String _strData,int _nX,int _nY) {
		this._strUniqueID = _strUniqueID ;
		this._strData = _strData;
		this._nX = _nX;
		this._nY = _nY;
		this.visited = false;
	}
	
	public String getUniqueID() {
		return _strUniqueID;
	}

	public String getData() {
		return _strData;
	}

	public int getX() {
		return _nX;
	}

	public int getY() {
		return _nY;
	}
	
	public boolean getVisited() {
		return visited;
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
}
