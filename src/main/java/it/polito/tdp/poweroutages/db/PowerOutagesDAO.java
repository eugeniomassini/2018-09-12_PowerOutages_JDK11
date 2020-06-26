package it.polito.tdp.poweroutages.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.polito.tdp.poweroutages.model.Arco;
import it.polito.tdp.poweroutages.model.Nerc;

public class PowerOutagesDAO {

	public List<Nerc> loadAllNercs(Map<Integer, Nerc> idMap) {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
				idMap.put(n.getId(), n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}

	public List<Arco> getArchi(Map<Integer, Nerc> idMap) {
		final String sql = "select nr.`nerc_one` as uno, nr.`nerc_two` as due, count(DISTINCT month(p1.`date_event_began`)) as peso " + 
				"from `NercRelations` as nr, `PowerOutages`as p1, PowerOutages as p2 " + 
				"where nr.`nerc_one`=p1.`nerc_id` " + 
				"and nr.`nerc_two`=p2.`nerc_id` " + 
				"and year(p1.`date_event_began`)=year(p2.`date_event_began`) " + 
				"and month(p1.`date_event_began`)=month(p2.`date_event_began`) " + 
				"group by nr.`nerc_one`, nr.`nerc_two`";

		List<Arco> arcList = new ArrayList<>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Arco n = new Arco(idMap.get(res.getInt("uno")), idMap.get(res.getInt("due")), res.getDouble("peso"));
				arcList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return arcList;
	}

}

