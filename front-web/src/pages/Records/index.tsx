import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './styles.css';
import { RecordResponse } from './types';
import { formatDate } from './helpers';
import Pagination from './pagination/index';
import { Link } from 'react-router-dom';

const BASE_URL = 'http://localhost:8080';

const Records = () => {

  // useState retorna um valor stateful e uma função para alterá-lo
  const [ recordsResponse, setRecordsReponse ] = useState<RecordResponse>();
  const [ activePage, setActivePage ] = useState(0);

  // Executa durante a inicialização do componente
  useEffect(() => {
    axios.get(`${BASE_URL}/records?linesPerPage=12&page=${activePage}`)
      .then(response => setRecordsReponse(response.data));
  }, [activePage]);

  const handlePageChange = (index: number) => {
    setActivePage(index)
  }

  return (
    <div className="page-container">
      <div className="filters-container records-actions">
        <Link to="/charts">
          <button className="action-filters">
            Ver gráfico
          </button>
        </Link>
      </div>
      <table className="records-table" cellPadding="0" cellSpacing="0">
        <thead>
          <tr>
            <th>INSTANTE</th>
            <th>NOME</th>
            <th>IDADE</th>
            <th>PLATAFORMA</th>
            <th>GÊNERO</th>
            <th>TÍTULO</th>
          </tr>
        </thead>
        <tbody>
          {recordsResponse?.content.map(record => (
            <tr key={record.id}>
              <td>{formatDate(record.moment)}</td>
              <td>{record.name}</td>
              <td>{record.age}</td>
              <td className="text-secondary">{record.gamePlatform}</td>
              <td>{record.genreName}</td>
              <td className="text-primary">{record.gameTitle}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <Pagination 
        activePage = {activePage}
        totalPages = {recordsResponse?.totalPages}
        goToPage = {handlePageChange}
      />
    </div>
  )
};

export default Records;