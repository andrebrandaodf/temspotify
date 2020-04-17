package br.com.professorisidro.temspotify.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.com.professorisidro.temspotify.model.Musica;

public class MusicaDAO implements GenericDAO{
	
	private DataSource dataSource;
	
	public MusicaDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void create(Object o) {
		try {
			
			if(o instanceof Musica) {
				Musica musica = (Musica)o;
				String SQL = "INSERT INTO tblMusica VALUES (null, ?, ?, ?, ?, ?)";
				PreparedStatement stm = dataSource.getConnection().prepareStatement(SQL);
				stm.setString(1, musica.getTitulo());
				stm.setString(2, musica.getArtista());
				stm.setString(3, musica.getAlbum());
				stm.setInt   (4, musica.getEstilo());
				stm.setString(5, musica.getLinkMP3());
				stm.executeUpdate();
				stm.close();
			}
		}
		catch(SQLException ex) {
			ex.printStackTrace();
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
		// TODO Auto-generated method stub
		return null;
	}
}
