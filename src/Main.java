
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.lang.String;

public class Main {
    private ArrayList<String> itemList;//хранит все бе элементы
    private JList<String> list;//отображения
    private DefaultListModel<String> listuprav;


    public Main() {
        itemList = new ArrayList<>(); //создание нового списка
        listuprav = new DefaultListModel<>();

        // Создаем и настраиваем главное окно приложения
        JFrame frame = new JFrame("операции");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);

        // Создаем список и добавляем его на панель
        list = new JList<>(listuprav);
        frame.add(new JScrollPane(list), BorderLayout.CENTER);

        // Создаем панель для кнопок операций
        JPanel buttonPanel = new JPanel();
        frame.add(buttonPanel, BorderLayout.SOUTH);

        JButton addButton = new JButton("Добавить");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newItem = JOptionPane.showInputDialog(frame, "Введите новый элемент:");
                if (newItem != null && !newItem.isEmpty()) {
                    itemList.add(newItem);
                    updateList();
                }
            }
        });
        buttonPanel.add(addButton);




// Кнопка "Поменять местами"

        JButton swapButton = new JButton("Менять местами");
        swapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(null, "Выберите элемент для замены.");
                    return;
                }

                JDialog dialog = new JDialog();
                dialog.setTitle("Введите индекс элемента для замены:");
                JTextField indexField = new JTextField(10);
                JButton okButton = new JButton("ОК");
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String input = indexField.getText();
                        try {
                            int swapIndex = Integer.parseInt(input);
                            if (swapIndex < 0 || swapIndex >= itemList.size()) {
                                throw new NumberFormatException();
                            }
                            if (selectedIndex == swapIndex) {
                                JOptionPane.showMessageDialog(null, "Выбранный элемент и элемент для замены не могут быть одним и тем же.");
                                return;
                            }
                            String temp = itemList.get(selectedIndex);
                            itemList.set(selectedIndex, itemList.get(swapIndex));
                            itemList.set(swapIndex, temp);
                            updateList();
                            dialog.dispose();
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Введите корректный индекс элемента.");
                        }
                    }
                });

                JPanel panel = new JPanel();
                panel.add(indexField);
                panel.add(okButton);
                dialog.add(panel);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });

        buttonPanel.add(swapButton);





        // Кнопка "Очистить все"
        JButton clearButton = new JButton("Очистить все");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                itemList.clear();
                updateList();
            }
        });
        buttonPanel.add(clearButton);
        // Кнопка "Удалить"
        JButton removeButton = new JButton("Удалить");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex >= 0) {
                    itemList.remove(selectedIndex);
                    updateList();
                }
            }
        });
        buttonPanel.add(removeButton);



        // Кнопка "Заменить"
        JButton replaceButton = new JButton("Заменить");
        replaceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex >= 0) {
                    String newItem = JOptionPane.showInputDialog(frame, "Введите новое значение:");
                    if (newItem != null && !newItem.isEmpty()) {
                        itemList.set(selectedIndex, newItem);
                        updateList();
                    }
                }
            }
        });
        buttonPanel.add(replaceButton);

        frame.setVisible(true);
    }


    private void updateList() {
        listuprav.clear();
        for (String item : itemList) {
            listuprav.addElement(item);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}

