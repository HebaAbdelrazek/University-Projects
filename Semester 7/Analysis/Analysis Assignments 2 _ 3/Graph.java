import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

import javax.sound.midi.MidiChannel;
import javax.swing.text.StyledEditorKit.ForegroundAction;

public class Graph {
	ArrayList<Vertex> vertices = new ArrayList();
	Vector<PathSegment> Path = new Vector<>();
	ArrayList<ArrayList<Edge>> edges = new ArrayList<ArrayList<Edge>>();

	public String getLibraryName() {
		return "OurGraphLibrary";
	}

	public String getLibraryVersion() {
		return "version 1.0";
	}

	public void insertVertex(String strUniqueID, String strData, int nX, int nY) throws GraphException {
		Vertex v = new Vertex(strUniqueID, strData, nX, nY);

		ArrayList<Edge> newEdges = new ArrayList<Edge>();

		for (int i = 0; i < vertices.size(); i++) {
			newEdges.add(null);
		}

		edges.add(newEdges);
		vertices.add(v);

		for (int i = 0; i < edges.size(); i++)
			edges.get(i).add(null);

	}

	public void insertEdge(String strVertex1UniqueID, String strVertex2UniqueID, String strEdgeUniqueID,
			String strEdgeData, int nEdgeCost) throws GraphException {

		Edge e = new Edge(strEdgeUniqueID, strEdgeData, nEdgeCost);

		int vertexIndex1 = 0;
		int vertexIndex2 = 0;

		for (int i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).getUniqueID().equals(strVertex1UniqueID)) {
				vertexIndex1 = i;
			} else if (vertices.get(i).getUniqueID().equals(strVertex2UniqueID)) {
				vertexIndex2 = i;
			}
		}

		edges.get(vertexIndex1).set(vertexIndex2, e);
		edges.get(vertexIndex2).set(vertexIndex1, e);
	}

	public void removeVertex(String strVertexUniqueID) throws GraphException {
		int i = 0;
		for (i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).getUniqueID().equals(strVertexUniqueID)) {
				vertices.remove(i);
				break;
			}

		}

		for (int j = 0; j < edges.size(); j++) {
			edges.get(j).remove(i);
		}

		edges.remove(i);

	}

	public void removeEdge(String strEdgeUniqueID) throws GraphException {
		for (int i = 0; i < edges.size(); i++) {
			for (int j = 0; j < edges.size(); j++) {
				if (edges.get(i).get(j) != null && edges.get(i).get(j).getUniqueID().equals(strEdgeUniqueID)) {
					edges.get(i).set(j, null);
					edges.get(j).set(i, null);

				}
			}
		}
	}

	public Vector<Edge> incidentEdges(String strVertexUniqueID) throws GraphException {
		Vector v = new Vector();

		int i = 0;
		for (i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).getUniqueID().equals(strVertexUniqueID)) {
				break;
			}
		}

		for (int j = 0; j < edges.size(); j++) {
			if (j == i) {
				for (int k = 0; k < edges.get(j).size(); k++) {
					if (edges.get(j).get(k) != null) {
						v.add(edges.get(j).get(k));
					}
				}
			}
		}

		return v;
	}

	public Vector<Vertex> vertices() throws GraphException {
		Vector v = new Vector();

		int i = 0;
		for (i = 0; i < vertices.size(); i++) {
			v.add(vertices.get(i));
		}

		return v;
	}

	public Vector<Edge> edges() throws GraphException {
		Vector v = new Vector();

		int i = 0;
		for (i = 0; i < edges.size(); i++) {
			for (int j = 0; j < edges.get(i).size(); j++) {
				if (edges.get(i).get(j) != null && (!v.contains(edges.get(i).get(j))))
					v.add(edges.get(i).get(j));
			}
		}

		return v;
	}

	public Vertex[] endVertices(String strEdgeUniqueID) throws GraphException {
		int vertex1 = 0;
		int vertex2 = 0;

		for (int i = 0; i < edges.size(); i++) {
			for (int j = 0; j < edges.get(i).size(); j++) {
				if (edges.get(i).get(j) != null && edges.get(i).get(j).getUniqueID().equals(strEdgeUniqueID)) {
					vertex1 = i;
					vertex2 = j;
				}
			}
		}

		Vertex[] v = new Vertex[2];
		v[0] = vertices.get(vertex1);
		v[1] = vertices.get(vertex2);

		return v;
	}

	public Vertex opposite(String strVertexUniqueID, String strEdgeUniqueID) throws GraphException {
		Vertex[] v = endVertices(strEdgeUniqueID);

		if (v[0].getUniqueID().equals(strVertexUniqueID)) {
			return v[1];
		} else {
			return v[0];
		}
	}

	public void dfs(String strStartVertexUniqueID, Visitor visitor) throws GraphException {
		int vertexIndex = 0;
		for (int i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).getUniqueID().equals(strStartVertexUniqueID)) {
				vertexIndex = i;
			}
		}

		dfsHelper(vertexIndex, visitor);

	}

	public void dfsHelper(int index, Visitor visitor) {
		boolean exists = false;
		vertices.get(index).setVisited(true);
		visitor.visit(vertices.get(index));

		boolean allNull = true;

		for (int i = 0; i < edges.get(index).size(); i++) {
			if (edges.get(index).get(i) != null) {

				for (int j = 0; j < Path.size(); j++) {
					if (Path.get(j).getEdge().equals(edges.get(index).get(i))) {
						if (Path.get(j).getVertex().equals(vertices.get(index))) {
							exists = true;
						}

					}
				}
				if (!exists) {
					PathSegment p = new PathSegment(vertices.get(index), edges.get(index).get(i));
					PathSegment p2 = new PathSegment(vertices.get(i), edges.get(index).get(i));

					Path.addElement(p);
					Path.addElement(p2);
				}
				allNull = false;
				break;
			}
		}

		boolean allVisited = true;

		for (int i = 0; i < edges.get(index).size(); i++) {
			if ((edges.get(index).get(i) != null) && (!vertices.get(i).getVisited())) {
				allVisited = false;
				break;
			}
		}

		if (allNull) {
			return;
		}

		else if (allVisited) {
			return;
		}

		else {

			for (int i = 0; i < edges.get(index).size(); i++) {

				if ((edges.get(index).get(i) != null) && (!vertices.get(i).getVisited())) {
					edges.get(index).get(i).setVisited(true);
					visitor.visit(edges.get(index).get(i));

					dfsHelper(i, visitor);
				}

			}

		}
	}

	public void bfs(String strStartVertexUniqueID, Visitor visitor) throws GraphException {
		// Getting the index of the root node in the array of nodes
		int vertexIndex = 0;
		for (int i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).getUniqueID().equals(strStartVertexUniqueID)) {
				vertexIndex = i;
			}
		}

		visitor.visit(vertices.get(vertexIndex));
		bfsHelper(vertexIndex, visitor);

	}

	public void bfsHelper(int index, Visitor visitor) throws GraphException {
		vertices.get(index).setVisited(true);
	
		boolean allNull = true;

		for (int i = 0; i < edges.get(index).size(); i++) {
			if (edges.get(index).get(i) != null) {
				allNull = false;
				break;
			}
		}

		boolean allVisited = true;

		for (int i = 0; i < edges.get(index).size(); i++) {
			if ((edges.get(index).get(i) != null) && (!vertices.get(i).getVisited())) {
				allVisited = false;
				break;
			}
		}

		if (allNull) {
			return;
		}

		else if (allVisited) {
			return;
		}

		else {

			Vector v;
			Vertex opposite;

			v = incidentEdges(vertices.get(index).getUniqueID());

			ArrayList<Vertex> oppositeVertices = new ArrayList<Vertex>();

			for (int j = 0; j < v.size(); j++) {
				opposite = opposite(vertices.get(index).getUniqueID(), ((Edge) v.get(j)).getUniqueID());

				if (!opposite.getVisited()) {
					oppositeVertices.add(opposite);
				}

			}

			int vIndex = 0;
			for (int l = 0; l < oppositeVertices.size(); l++) {
				for (int k = 0; k < vertices.size(); k++) {
					if (vertices.get(k).getUniqueID().equals(oppositeVertices.get(l).getUniqueID())) {
						vIndex = k;
						break;
					}
				}

				vertices.get(vIndex).setVisited(true);
				visitor.visit(vertices.get(vIndex));
				
			}

			int vIndex2 = 0;
			for (int m = 0; m < oppositeVertices.size(); m++) {
				for (int k = 0; k < vertices.size(); k++) {
					if (vertices.get(k).getUniqueID().equals(oppositeVertices.get(m).getUniqueID())) {
						vIndex2 = k;
						break;
					}
				}
				bfsHelper(vIndex2, visitor);
			}
		}

	}

	public Vector<PathSegment> pathDFS(String strStartVertexUniqueID, String strEndVertexUniqueID)
			throws GraphException {
		String edgeID = "";
		String vertexID = "";

		Vector<PathSegment> resultPath = new Vector<>();
		Vector<PathSegment> FinalPath = new Vector<>();

		boolean pathExists = false;

		GradingVisitor gVisitor = new GradingVisitor();

		dfs(strStartVertexUniqueID, gVisitor);
		for (int i = 0; i < Path.size(); i++) {
			if (Path.get(i).getVertex().getUniqueID().equals(strEndVertexUniqueID)) {
				pathExists = true;
				edgeID = Path.get(i).getEdge().getUniqueID();
				resultPath.add(Path.get(i));
				Path.remove(i);
				break;
			}

		}
		if (!pathExists) {
			return FinalPath;
		}

		while (vertexID != strStartVertexUniqueID) {
			for (int j = 0; j < Path.size(); j++) {
				if (Path.get(j).getEdge().getUniqueID().equals(edgeID)) {
					vertexID = Path.get(j).getVertex().getUniqueID();
					resultPath.add(Path.get(j));
					Path.remove(j);

					break;
				}
			}
			if (vertexID != strStartVertexUniqueID) {
				for (int k = 0; k < Path.size(); k++) {
					if (Path.get(k).getVertex().getUniqueID().equals(vertexID)) {
						edgeID = Path.get(k).getEdge().getUniqueID();
						resultPath.add(Path.get(k));
						Path.remove(k);

						break;
					}
				}
			}
		}
		for (int k = 0; k < resultPath.size(); k++) {
			boolean edgeExists = false;
			for (int m = 0; m < FinalPath.size(); m++) {
				if (FinalPath.get(m) != null && FinalPath.get(m).getVertex().getUniqueID()
						.equals(resultPath.get(resultPath.size() - k - 1).getVertex().getUniqueID())) {
					edgeExists = true;
					break;
				}

			}
			if (!edgeExists) {
				FinalPath.add(resultPath.get(resultPath.size() - 1 - k));
			}
		}
	
		return FinalPath;

	}

	public double euclideanDistance(Vertex v1, Vertex v2) {
		return Math.sqrt((v1.getX() - v2.getX()) ^ 2 - (v1.getY() - v2.getY()) ^ 2);
	}

	public Vertex[] closestPair() throws GraphException {

		ArrayList<Vertex> verticesByX = new ArrayList<>();
		verticesByX = vertices;
		ArrayList<Vertex> verticesByY = new ArrayList<>();

		Vertex min, tmp;
		for (int i = 0; i < verticesByX.size(); i++) {
			min = verticesByX.get(i);
			int j;
			int minIndex = i;
			for (j = i; j < verticesByX.size(); j++) {
				if (verticesByX.get(j).getX() < min.getX()) {
					min = verticesByX.get(j);
					minIndex = j;
				}
			}
			tmp = verticesByX.get(i);
			verticesByX.set(i, verticesByX.get(minIndex));
			verticesByX.set(minIndex, tmp);
		}

		Vertex bestVertex1 = vertices.get(0);
		Vertex bestVertex2 = vertices.get(1);

		double minDistance = euclideanDistance(vertices.get(0), vertices.get(1));
		double dist = closestPairHelper(verticesByX, minDistance, 0, verticesByX.size() - 1, bestVertex1, bestVertex2);
		Vertex[] v = { bestVertex1, bestVertex2 };
		return v;
	}

	public double closestPairHelper(ArrayList<Vertex> verticesByX, double minDistance, int low, int high,
			Vertex bestVertex1, Vertex bestVertex2) {

		if (verticesByX.size() <= 3) {
			if (verticesByX.size() == 2) {
				double dist = euclideanDistance(verticesByX.get(0), verticesByX.get(1));
				if (dist < minDistance) {
					minDistance = dist;
					bestVertex1 = verticesByX.get(0);
					bestVertex2 = verticesByX.get(1);
				}
			} else if (verticesByX.size() == 3) {
				double dist1 = euclideanDistance(verticesByX.get(0), verticesByX.get(1));
				double dist2 = euclideanDistance(verticesByX.get(0), verticesByX.get(2));
				double dist3 = euclideanDistance(verticesByX.get(1), verticesByX.get(2));

				if (dist1 < dist2 && dist1 < dist3) {
					minDistance = dist1;
					bestVertex1 = verticesByX.get(0);
					bestVertex2 = verticesByX.get(1);
				} else if (dist2 < dist1 && dist2 < dist3) {
					minDistance = dist2;
					bestVertex1 = verticesByX.get(0);
					bestVertex2 = verticesByX.get(2);
				} else {
					minDistance = dist3;
					bestVertex1 = verticesByX.get(1);
					bestVertex2 = verticesByX.get(2);
				}
			}
			return minDistance;

		}

		else {
			int mid = low + (high - low) / 2;

			ArrayList<Vertex> newVerticesByX1 = new ArrayList<Vertex>(), newVerticesByX2 = new ArrayList<Vertex>();

			for (int i = low; i <= mid; i++) {
				newVerticesByX1.add(verticesByX.get(i));
			}
			for (int i = mid + 1; i <= high; i++) {
				newVerticesByX2.add(verticesByX.get(i));
			}

			double minDistLeft = closestPairHelper(newVerticesByX1, minDistance, low, mid, bestVertex1, bestVertex2);
			double minDistRight = closestPairHelper(newVerticesByX2, minDistance, mid + 1, high, bestVertex1,
					bestVertex2);

			minDistance = minDistLeft < minDistRight ? minDistLeft : minDistRight;
			int range = (int) minDistance;

			ArrayList<Vertex> newVerticesByX3 = new ArrayList<Vertex>();
			for (int i = mid - range; i <= mid + range; i++) {
				newVerticesByX3.add(verticesByX.get(i));
			}

			double minDistMiddle = closestPairHelper(newVerticesByX3, minDistance, mid - range, mid + range,
					bestVertex1, bestVertex2);

			return minDistance < minDistMiddle ? minDistance : minDistMiddle;

		}
	}

	public boolean containsEdge(String edgeUniqueID) {
		for (int i = 0; i < edges.size(); i++) {
			for (int j = 0; j < edges.get(i).size(); j++) {
				if (edges.get(i).get(j) != null && edges.get(i).get(j).getUniqueID() == edgeUniqueID) {
					return true;
				}
			}
		}
		return false;
	}

	public Vector<PathSegment> minSpanningTree() throws GraphException {
		Vector<PathSegment> vector = new Vector<PathSegment>();
		ArrayList<Edge> increasingEdges = new ArrayList<Edge>();

		ArrayList<Edge> result = new ArrayList<Edge>();

		ArrayList<Vertex[]> correspondingVertices = new ArrayList<Vertex[]>();

		for (int i = 0; i < edges.size(); i++) {
			for (int j = 0; j < edges.get(i).size(); j++) {
				if (edges.get(i).get(j) != null && !(increasingEdges.contains(edges.get(i).get(j)))) {
					increasingEdges.add(edges.get(i).get(j));
				}
			}
		}

		Edge min, tmp;
		for (int i = 0; i < increasingEdges.size(); i++) {
			min = increasingEdges.get(i);
			int j;
			int minIndex = i;
			for (j = i; j < increasingEdges.size(); j++) {
				if (increasingEdges.get(j).getCost() < min.getCost()) {
					min = increasingEdges.get(j);
					minIndex = j;
				}
			}

			tmp = increasingEdges.get(i);
			increasingEdges.set(i, increasingEdges.get(minIndex));
			increasingEdges.set(minIndex, tmp);
		}

		for (int i = 0; i < increasingEdges.size(); i++) {
			correspondingVertices.add(endVertices(increasingEdges.get(i).getUniqueID()));
		}

		int count = 0;
		
		for (int i = 0; i < increasingEdges.size(); i++) {
			Edge edge = increasingEdges.get(i);
			String start = correspondingVertices.get(i)[0].getUniqueID();
			String end = correspondingVertices.get(i)[1].getUniqueID();
		
			boolean pathExists = false;

			GradingVisitor gVisitor = new GradingVisitor();

			dfs(start, gVisitor);
			for (int k = 0; k < Path.size(); k++) {
				if (Path.get(k).getVertex().getUniqueID().equals(end)) {
					pathExists = true;
					break;
				}

			}

			if (pathExists) {

				vector.add(new PathSegment(correspondingVertices.get(i)[0], edge));
				count++;

				if (count == vertices.size() - 1)
					break;
			}

			Path.clear();

		}

		return vector;
	}

	public Vector<Vector<PathSegment>> findAllShortestPathsFW() throws GraphException {
		int directcost = 100000000;
		int loopcost = 100000000;
		int minsofar = 100000000;
		int pathindex = 0;
		int vertex2 = 0;
		ArrayList<ArrayList<Integer>> tempedges = new ArrayList<ArrayList<Integer>>();
		Vector<Vector<PathSegment>> path = new Vector<Vector<PathSegment>>();

		for (int i = 0; i < vertices.size(); i++) {
			ArrayList<Integer> tempInt = new ArrayList<Integer>();
			for (int j = 0; j < vertices.size(); j++) {
				tempInt.add(null);

			}
			tempedges.add(tempInt);
		}
		for (int i = 0; i < vertices.size() * vertices.size(); i++) {
			Vector<PathSegment> temppath = new Vector<PathSegment>();
			for (int j = 0; j < vertices.size(); j++) {
				temppath.add(null);

			}
			path.add(temppath);
		}

		for (int i = 0; i < vertices.size(); i++) {
			for (int j = 0; j < edges.get(i).size(); j++) {
				if (edges.get(i).get(j) != null) {
					tempedges.get(i).set(j, edges.get(i).get(j).getCost());
				}
			}

		}

		if (pathindex == 0) {
			for (int i = 0; i < edges.get(0).size(); i++) {
				if (edges.get(0).get(i) != null) {
					path.get(pathindex).set(0, new PathSegment(vertices.get(i), edges.get(0).get(i)));
					tempedges.get(0).set(i, edges.get(0).get(i).getCost());
				} else {
					path.get(pathindex).set(0, null);
					tempedges.get(0).set(i, null);
				}
				pathindex++;

			}
		}

		for (int f = 0; f < 2; f++) {
			pathindex = 0;
			for (int i = 0; i < vertices.size(); i++) {

				for (int j = 0; j < edges.get(i).size(); j++) {
					minsofar = 100000000;

					if (!(j == i)) {
						if (tempedges.get(i).get(j) != null)
							directcost = tempedges.get(i).get(j);

						for (int k = 0; k < tempedges.get(i).size(); k++) {
							if (tempedges.get(i).get(k) != null) {
								if (tempedges.get(k).get(j) != null) {
									if (tempedges.get(i).get(k) + tempedges.get(k).get(j) < minsofar) {
										minsofar = tempedges.get(i).get(k) + tempedges.get(k).get(j);
										vertex2 = k;
									}
								}
							}
						}

						loopcost = minsofar;

						if (loopcost < directcost || tempedges.get(i).get(j) == null
								&& (tempedges.get(vertex2).get(j) != null && tempedges.get(i).get(vertex2) != null)) {

							PathSegment p1 = new PathSegment(vertices.get(vertex2), edges.get(i).get(vertex2));

							Vector<PathSegment> p = new Vector<PathSegment>();
							PathSegment p2 = path.get(((vertices.size()) * vertex2) + j).get(0);

							p.add(p1);

							for (int h = 0; h < (path.get(((vertices.size()) * vertex2) + j).size()); h++) {

								if ((path.get(((vertices.size()) * vertex2) + j)).get(h) != null) {

									PathSegment p3 = path.get(((vertices.size()) * vertex2) + j).get(h);
									p.add(p3);
								}
							}

							// p.add(p2);
							for (int l = 0; l < path.get(pathindex).size(); l++) {
								if (path.get(pathindex).get(l) != null && path.get(pathindex).get(l).getEdge() != null
										&& path.get(pathindex).get(l).getVertex() != null) {
									path.get(pathindex).set(l, null);
								}
							}
							path.set(pathindex, p);

							tempedges.get(i).set(j, loopcost + directcost);

						} else if (tempedges.get(i).get(j) != null) {
							PathSegment p1 = new PathSegment(vertices.get(j), edges.get(j).get(i));
							Vector<PathSegment> p = new Vector<PathSegment>();
							p.add(p1);
							for (int l = 0; l < path.get(pathindex).size(); l++) {
								if (path.get(pathindex).get(l) != null && path.get(pathindex).get(l).getEdge() != null
										&& path.get(pathindex).get(l).getVertex() != null) {

									path.get(pathindex).set(l, null);
								}
							}
							path.set(pathindex, p);

						}
						directcost = 100000000;
						loopcost = 100000000;

					}
					pathindex++;
				}
			}
		}

		return path;

	}

	public ArrayList<ArrayList<PathSegment>> findShortestPathBF(String strStartVertexUniqueID) throws GraphException {
		int[] costs = new int[vertices.size()];
		ArrayList<ArrayList<PathSegment>> result = new ArrayList<ArrayList<PathSegment>>();

		for (int i = 0; i < costs.length; i++) {
			result.add(new ArrayList<PathSegment>());
		}

		int startingIndex = 0;
		for (int i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).getUniqueID() == strStartVertexUniqueID)
				startingIndex = i;
		}

		for (int i = 0; i < costs.length; i++) {
			if (i == startingIndex)
				costs[i] = 0;
			else
				costs[i] = 100000;
		}

		for (int i = 1; i < vertices.size(); i++) {
			for (int j = 0; j < edges.size(); j++) {
				for (int k = 0; k < edges.get(i).size(); k++) {

					if (edges.get(j).get(k) != null) {
						int edgeCost = edges.get(j).get(k).getCost();

						if ((costs[j] != 100000) && (costs[j] + edgeCost < costs[k])) {
							costs[k] = costs[j] + edgeCost;
							if (j != startingIndex) {
								for (int l = 0; l < result.get(j).size(); l++) {
									result.get(k).add(result.get(j).get(l));
								}
							}
							PathSegment pathSegment = new PathSegment(vertices.get(j), edges.get(j).get(k));
							result.get(k).add(pathSegment);
						}
					}

				}
			}
		}

		return result;

	}

}
