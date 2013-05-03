package app;

import app.entities.Reservations;
import app.entities.Rooms;
import app.entities.Users;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author noxx
 */
public class OverlapingReservationsPanel extends javax.swing.JPanel {

    private EntityManagerFactory factory;
    private List<Reservations> loadedReservations;

    /**
     * Creates new form ReservationsPanel
     */
    public OverlapingReservationsPanel() {
        initComponents();
    }

    public void initialize(EntityManagerFactory factory) {
        this.factory = factory;
    }

    private List<Users> getUsersList() {
        EntityManager em = factory.createEntityManager();
        CriteriaQuery<Users> cq = em.getCriteriaBuilder().createQuery(Users.class);
        cq.select(cq.from(Users.class));
        return em.createQuery(cq).getResultList();
    }

    private List<Rooms> getRoomsList() {
        EntityManager em = factory.createEntityManager();
        CriteriaQuery<Rooms> cq = em.getCriteriaBuilder().createQuery(Rooms.class);
        cq.select(cq.from(Rooms.class));
        return em.createQuery(cq).getResultList();
    }

    private void refreshTable() {
        EntityManager em = factory.createEntityManager();
        CriteriaQuery<Reservations> cq = em.getCriteriaBuilder().createQuery(Reservations.class);
        cq.select(cq.from(Reservations.class));
        loadedReservations = em.createQuery(cq).getResultList();

        DefaultTableModel m = new ReservationsTableModel();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        for (Reservations res : loadedReservations) {
            String[] row = {res.getUserId().getLogin(), res.getRoomId().getCode(),
                df.format(res.getStart()), df.format(res.getFinish())};
            m.addRow(row);
        }
        em.close();
        reservationsTable.setModel(m);    
    }
    
    public void refreshData(){
        refreshTable();
    }

    class ReservationsTableModel extends DefaultTableModel {

        public ReservationsTableModel() {
            super();
            addColumn("User");
            addColumn("Room");
            addColumn("From");
            addColumn("To");
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        isolationLevelLabel = new javax.swing.JLabel();
        readCommitedRadioB = new javax.swing.JRadioButton();
        serializableRadioB = new javax.swing.JRadioButton();
        StartTransactionsButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        reservationsLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        reservationsTable = new javax.swing.JTable();

        isolationLevelLabel.setText("Level of isolation:");

        buttonGroup1.add(readCommitedRadioB);
        readCommitedRadioB.setSelected(true);
        readCommitedRadioB.setText("READ COMMITED");

        buttonGroup1.add(serializableRadioB);
        serializableRadioB.setText("SERIALIZABLE");

        StartTransactionsButton.setText("Start 2 inserting threads");
        StartTransactionsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StartTransactionsButtonMouseClicked(evt);
            }
        });

        jButton1.setText("Refresh table");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(readCommitedRadioB)
                    .addComponent(serializableRadioB)
                    .addComponent(isolationLevelLabel)
                    .addComponent(StartTransactionsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(isolationLevelLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(readCommitedRadioB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(serializableRadioB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(StartTransactionsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        reservationsLabel.setText("All reservations:");

        reservationsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "user", "room", "from", "to", "requested"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(reservationsTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reservationsLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(reservationsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void StartTransactionsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StartTransactionsButtonMouseClicked
        List<Users> users = null;
        List<Rooms> rooms = null;
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            users = getUsersList();
            rooms = getRoomsList();
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }
        boolean isolationLevel = serializableRadioB.isSelected();
        if (users != null) {
            Thread t1 = new ReservationInsertThread("t1", isolationLevel, factory, users, rooms);
            Thread t2 = new ReservationInsertThread("t2", isolationLevel, factory, users, rooms);
            t1.start();
            t2.start();
            while (t1.isAlive()||t2.isAlive()){
            }
            refreshData();
        }
        
    }//GEN-LAST:event_StartTransactionsButtonMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        refreshData();
    }//GEN-LAST:event_jButton1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton StartTransactionsButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel isolationLevelLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton readCommitedRadioB;
    private javax.swing.JLabel reservationsLabel;
    private javax.swing.JTable reservationsTable;
    private javax.swing.JRadioButton serializableRadioB;
    // End of variables declaration//GEN-END:variables
}
