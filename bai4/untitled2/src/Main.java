import entity.entity.Phim;
import service.CategoryService;
import service.imp;

public class Main {
    public static void main(String[] args) {
        CategoryService categoryService = new imp();
        //for(Phim p: categoryService.findAll()) {
           // System.out.println(p.getId() + " -- " + p.getName() + " -- " + p.getCategory() + " -- " + p.getTime());
        //}
        //Phim phim = new Phim();
        //phim.setId(11);
        //phim.setName("Há Lô");
        //phim.setCategory("Hài Kinh Dị");
        //phim.setTime(1);
        //categoryService.insert(phim);

       // Phim phim = new Phim();
       // phim.setId(11);
      //  categoryService.delete(phim);


     //  Phim phim = new Phim();
        //  phim.setId(7);
        // phim.setName("Há Lô 1");
        // phim.setCategory("Hài Kinh ị");
        // phim.setTime(1);
        // categoryService.update(phim);

        Phim phim = new Phim();
        categoryService.findById(11);

        for (Phim p: categoryService.findAll()) {
            System.out.println(p.getId() + " -- " + p.getName() + " -- " + p.getCategory() + " -- " + p.getTime());
        }
    }
}
