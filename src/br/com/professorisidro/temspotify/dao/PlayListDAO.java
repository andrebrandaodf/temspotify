package br.com.professorisidro.temspotify.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.professorisidro.temspotify.model.PlayList;

public class PlayListDAO implements GenericDAO {

	private DataSource dataSource;
	
	public PlayListDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	@Override
	public void create(Object o) {
		try {
			PlayList pl = (PlayList)o;
			String SQL = "INSERT INTO tblPlaylist VALUES (null, ?, ?)";
			PreparedStatement stm = dataSource.getConnection().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, pl.getTitulo());
			stm.setInt(2, pl.getUsuario().getId());
			int res = stm.executeUpdate();
			
			if(res == 0) {
				throw new RuntimeException("Nao foi possivdel incluir playlist");
			}
			ResultSet rs = stm.getGeneratedKeys();
				if(rs.next()) {
					pl.setId(rs.getInt(1));
				}
			
		}
		catch(SQLException ex) {
			System.out.println("Erro ao cadastrar PlayList "+ex.getMessage());
		}
	
	}

	@Override
	public void update(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Object> read(Object o) {
		
		try {
			String SQL = "SELECT * FROM tblPlaylist WHERE idUsuario = ?";
			Integer idUser = (Integer) o;
			PreparedStatement stm = dataSource.getConnection().prepareStatement(SQL);
			stm.setInt(1, idUser.intValue());
			ResultSet rs = stm.executeQuery();
			ArrayList<Object> list = new ArrayList<Object>();
			while(rs.next()) {
				PlayList pl = new PlayList();
				pl.setId(rs.getInt("idPLaylist"));
				pl.setTitulo(rs.getString("titulo"));
				list.add(pl);
			}
			rs.close();
			stm.close();
			return list;
		} 
		catch (SQLException ex) {
			System.out.println("Erro ao recuperar Playlists "+ex.getMessage());
		}
		
		return null;
	}
	
}
