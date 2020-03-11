public class Edge {
	protected String _strUniqueID, _strData;
	protected int _nEdgeCost;
	protected boolean visited;
	
	public Edge(String _strUniqueID, String _strData,int _nEdgeCost) {
		this._strUniqueID = _strUniqueID;
		this._strData = _strData;
		this._nEdgeCost = _nEdgeCost;
		this.visited = false;
	}
	
	public String getUniqueID( ){
		return _strUniqueID;
	}

	public String getData( ){
		return _strData;
	}

	public int getCost() {
		return _nEdgeCost;
	}
	
	public boolean getVisited() {
		return visited;
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
}